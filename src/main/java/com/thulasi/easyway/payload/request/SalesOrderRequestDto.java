package com.thulasi.easyway.payload.request;

import com.thulasi.easyway.type.SalesOrderStatus;
import com.thulasi.easyway.type.SalesOrderType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SalesOrderRequestDto {
    private SalesOrderType type;
    private SalesOrderStatus status;
    List<Long> cardItemIds;
    private BigDecimal totalQuantity;
    private BigDecimal totalWeight;
    private Long customerId;
    private Long salesPersonId;
    private Long deliveryPersonId;
}
