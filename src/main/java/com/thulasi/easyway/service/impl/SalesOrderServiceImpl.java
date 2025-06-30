package com.thulasi.easyway.service.impl;

import com.thulasi.easyway.constant.CommonMessageConstant;
import com.thulasi.easyway.exception.ModuleException;
import com.thulasi.easyway.model.*;
import com.thulasi.easyway.payload.request.SalesOrderRequestDto;
import com.thulasi.easyway.payload.response.BuyingPriceDetail;
import com.thulasi.easyway.payload.response.ResponseEntityDto;
import com.thulasi.easyway.payload.response.SalesOrderResponseDto;
import com.thulasi.easyway.repository.*;
import com.thulasi.easyway.service.SalesOrderService;
import com.thulasi.easyway.service.UserService;
import com.thulasi.easyway.type.SalesOrderStatus;
import com.thulasi.easyway.type.SalesOrderType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class SalesOrderServiceImpl implements SalesOrderService {

    private final SalesOrderRepository salesOrderRepository;
    private final CardItemRepository cardItemRepository;
    private final StockRepository stockRepository;
    private final StockIncomeRepository stockIncomeRepository;
    private final StockOutgoingRepository stockOutgoingRepository;
    private final SalesOrderItemRepository salesOrderItemRepository;
    private final UserService userService;
    private final InvoiceRepository invoiceRepository;

    @Transactional
    public ResponseEntityDto createSalesOrder(SalesOrderRequestDto dto) {
        log.info("createSalesOrder: execution started");

        User currentUser = userService.getCurrentUser();
        List<CardItem> cardItems = cardItemRepository.findByIdInAndUserId(dto.getCardItemIds(), currentUser.getId());

        if (cardItems.isEmpty()) {
            throw new ModuleException(CommonMessageConstant.COMMON_ERROR_CARD_ITEMS_NOT_FOUND);
        }

        User customer;
        if (dto.getType().equals(SalesOrderType.ONLINE)) {
            customer = currentUser;
        } else if (dto.getCustomerId() != null) {
            customer = User.builder().id(dto.getCustomerId()).build();
        } else {
            customer = null;
        }

        SalesOrder salesOrder = SalesOrder.builder()
                .customer(customer)
                .status(dto.getStatus())
                .type(dto.getType())
                .totalWeight(dto.getTotalWeight())
                .totalQuantity(dto.getTotalQuantity())
                .salesPerson(dto.getSalesPersonId() != null ? User.builder().id(dto.getSalesPersonId()).build() : null)
                .deliveryPerson(dto.getDeliveryPersonId() != null ? User.builder().id(dto.getDeliveryPersonId()).build() : null)
                .totalBuyingAmount(BigDecimal.ZERO)
                .totalSellingAmount(BigDecimal.ZERO)
                .totalProfitAmount(BigDecimal.ZERO)
                .build();

        salesOrder = salesOrderRepository.save(salesOrder);

        BigDecimal totalBuyingAmount = BigDecimal.ZERO;
        BigDecimal totalSellingAmount = BigDecimal.ZERO;
        BigDecimal totalProfitAmount = BigDecimal.ZERO;

        for (CardItem cardItem : cardItems) {
            Stock stock = stockRepository.findByProductId(cardItem.getProduct().getId())
                    .orElseThrow(() -> new ModuleException(CommonMessageConstant.COMMON_ERROR_STOCK_NOT_FOUND));

            BigDecimal quantity = cardItem.getQuantity();
            BigDecimal unitSellingPrice = getDefaultSellingPrice(stock);
            List<BuyingPriceDetail> buyingPriceDetails = getBuyingPriceDetails(stock, quantity);

            BigDecimal buyingTotal = getTotalBuyingPrice(buyingPriceDetails);
            BigDecimal sellingTotal = unitSellingPrice.multiply(quantity);
            BigDecimal profit = sellingTotal.subtract(buyingTotal);

            SalesOrderItem orderItem = SalesOrderItem.builder()
                    .salesOrder(salesOrder)
                    .stock(stock)
                    .quantity(quantity)
                    .measurementUnit(stock.getProduct().getMeasurementUnit())
                    .unitSellingPrice(unitSellingPrice)
                    .unitBuyingPriceDetails(buyingPriceDetails)
                    .totalSellingAmount(sellingTotal)
                    .totalBuyingAmount(buyingTotal)
                    .totalProfitAmount(profit)
                    .build();

            salesOrderItemRepository.save(orderItem);

            if (dto.getStatus() == SalesOrderStatus.ORDER_CONFIRMED) {
                stock.setReservedQuantity(stock.getReservedQuantity().add(quantity));
                stockRepository.save(stock);
            }

            totalBuyingAmount = totalBuyingAmount.add(buyingTotal);
            totalSellingAmount = totalSellingAmount.add(sellingTotal);
            totalProfitAmount = totalProfitAmount.add(profit);
        }

        salesOrder.setTotalSellingAmount(totalSellingAmount);
        salesOrder.setTotalBuyingAmount(totalBuyingAmount);
        salesOrder.setTotalProfitAmount(totalProfitAmount);
        salesOrderRepository.save(salesOrder);

        cardItemRepository.deleteAllById(dto.getCardItemIds());

        Invoice invoice = Invoice.builder()
                .salesOrder(salesOrder)
                .invoiceAmount(totalSellingAmount)
                .build();

        invoiceRepository.save(invoice);

        SalesOrderResponseDto salesOrderResponseDto = SalesOrderResponseDto.builder()
                .orderId(salesOrder.getId())
                .orderRefNo(salesOrder.getOrderRefNo())
                .invoiceId(invoice.getId())
                .invoiceRefNo(invoice.getInvoiceRefNo())
                .amount(totalSellingAmount)
                .build();

        log.info("createSalesOrder: execution completed");
        return new ResponseEntityDto(true, salesOrderResponseDto);
    }


    @Transactional
    public ResponseEntityDto updateSalesOrderStatus(Long id, SalesOrderStatus status) {
        SalesOrder order = salesOrderRepository.findById(id)
                .orElseThrow(() -> new ModuleException(CommonMessageConstant.COMMON_ERROR_SALES_ORDER_NOT_FOUND));

        if (status == SalesOrderStatus.PAYMENT_COMPLETED) {
            for (SalesOrderItem item : order.getSalesOrderItems()) {
                Stock stock = item.getStock();
                List<BuyingPriceDetail> buyingPriceDetails = item.getUnitBuyingPriceDetails();

                createStockOutgoing(order, stock, buyingPriceDetails, item.getUnitSellingPrice());
                stock.setQuantity(stock.getQuantity().subtract(item.getQuantity()));
                stock.setReservedQuantity(stock.getReservedQuantity().subtract(item.getQuantity()));
                stockRepository.save(stock);
            }
        } else if (status == SalesOrderStatus.ORDER_CANCELLED || status == SalesOrderStatus.DELIVERY_CANCELLED) {
            for (SalesOrderItem item : order.getSalesOrderItems()) {
                Stock stock = item.getStock();
                stock.setReservedQuantity(stock.getReservedQuantity().subtract(item.getQuantity()));
                stockRepository.save(stock);
            }
        }

        order.setStatus(status);
        salesOrderRepository.save(order);

        return new ResponseEntityDto("Order status updated successfully", true);
    }

    @Transactional
    public ResponseEntityDto assignDeliveryPerson(Long id, Long deliveryPersonId) {
        SalesOrder order = salesOrderRepository.findById(id)
                .orElseThrow(() -> new ModuleException(CommonMessageConstant.COMMON_ERROR_SALES_ORDER_NOT_FOUND));

        order.setDeliveryPerson(User.builder().id(deliveryPersonId).build());
        salesOrderRepository.save(order);
        return new ResponseEntityDto("Delivery person assigned successfully", true);
    }

    private BigDecimal getDefaultSellingPrice(Stock stock) {
        return stock.getPrices().stream()
                .filter(Price::getIsDefault)
                .map(Price::getFinalPrice)
                .findFirst()
                .orElse(BigDecimal.ZERO);
    }

    private BigDecimal getTotalBuyingPrice(List<BuyingPriceDetail> buyingPriceDetails) {
        return buyingPriceDetails.stream()
                .map(detail -> detail.getUnitBuyingPrice().multiply(detail.getQuantity()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private List<BuyingPriceDetail> getBuyingPriceDetails(Stock stock, BigDecimal requiredQty) {
        BigDecimal remaining = requiredQty;
        List<BuyingPriceDetail> result = new ArrayList<>();

        List<StockIncome> incomes = stock.getStockIncomes().stream()
                .filter(income -> income.getAvailableQuantity().compareTo(BigDecimal.ZERO) > 0)
                .sorted(Comparator.comparing(StockIncome::getCreatedDate))
                .toList();

        for (StockIncome income : incomes) {
            if (remaining.compareTo(BigDecimal.ZERO) <= 0) break;

            BigDecimal available = income.getAvailableQuantity();
            BuyingPriceDetail detail = BuyingPriceDetail.builder()
                    .unitBuyingPrice(income.getUnitBuyingPrice())
                    .build();

            if (available.compareTo(remaining) >= 0) {
                detail.setQuantity(remaining);
                result.add(detail);
                income.setAvailableQuantity(available.subtract(remaining));
                stockIncomeRepository.save(income);
                break;
            } else {
                detail.setQuantity(available);
                result.add(detail);
                remaining = remaining.subtract(available);
                income.setAvailableQuantity(BigDecimal.ZERO);
                stockIncomeRepository.save(income);
            }
        }

        return result;
    }

    private void createStockOutgoing(SalesOrder order, Stock stock, List<BuyingPriceDetail> buyingPriceDetails, BigDecimal sellingPrice) {
        for (BuyingPriceDetail detail : buyingPriceDetails) {
            BigDecimal quantity = detail.getQuantity();
            BigDecimal buying = detail.getUnitBuyingPrice();
            BigDecimal totalBuy = buying.multiply(quantity);
            BigDecimal totalSell = sellingPrice.multiply(quantity);
            BigDecimal profit = totalSell.subtract(totalBuy);

            StockOutgoing stockOutgoing = StockOutgoing.builder()
                    .salesOrder(order)
                    .stock(stock)
                    .quantity(quantity)
                    .unitBuyingPrice(buying)
                    .unitSellingPrice(sellingPrice)
                    .totalBuyingAmount(totalBuy)
                    .totalSellingAmount(totalSell)
                    .profitAmount(profit)
                    .build();

            stockOutgoingRepository.save(stockOutgoing);
        }
    }
}
