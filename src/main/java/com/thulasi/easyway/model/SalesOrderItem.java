package com.thulasi.easyway.model;

import java.math.BigDecimal;
import java.util.List;

import com.thulasi.easyway.payload.response.BuyingPriceDetail;
import com.thulasi.easyway.type.MeasurementUnit;

import com.thulasi.easyway.util.converter.StringListConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sales_order_items")
public class SalesOrderItem extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "sales_order_id", nullable = false)
	private SalesOrder salesOrder;

	@ManyToOne
	@JoinColumn(name = "stock_id", nullable = false)
	private Stock stock;

	@Column(name = "quantity", nullable = false)
	private BigDecimal quantity;

	@Column(name = "product_unit", nullable = false)
	@Enumerated(EnumType.STRING)
	private MeasurementUnit measurementUnit;

	@Convert(converter = StringListConverter.class)
	@Column(name = "unit_buying_price_details", columnDefinition = "text")
	private List<BuyingPriceDetail> unitBuyingPriceDetails;

	@Column(name = "unit_selling_price", nullable = false)
	private BigDecimal unitSellingPrice;

	@Column(name = "total_buying_amount", nullable = false)
	private BigDecimal totalBuyingAmount;

	@Column(name = "total_selling_amount", nullable = false)
	private BigDecimal totalSellingAmount;

	@Column(name = "total_profit_amount", nullable = false)
	private BigDecimal totalProfitAmount;

}
