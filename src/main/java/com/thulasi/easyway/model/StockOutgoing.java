package com.thulasi.easyway.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Table(name = "stock_outgoings")
public class StockOutgoing extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "stock_id", nullable = false)
	private Stock stock;

	@ManyToOne
	@JoinColumn(name = "sales_order_id", nullable = true)
	private SalesOrder salesOrder;

	@Column(name = "quantity", nullable = false)
	private BigDecimal quantity;

	@Column(name = "unit_buying_price", nullable = false)
	private BigDecimal unitBuyingPrice;

	@Column(name = "unit_selling_price", nullable = false)
	private BigDecimal unitSellingPrice;

	@Column(name = "total_buying_amount", nullable = false)
	private BigDecimal totalBuyingAmount;

	@Column(name = "total_selling_amount", nullable = false)
	private BigDecimal totalSellingAmount;

	@Column(name = "profit_amount", nullable = false)
	private BigDecimal profitAmount;

}
