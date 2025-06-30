package com.thulasi.easyway.model;

import java.math.BigDecimal;
import java.time.LocalDate;

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
@Table(name = "stock_incomes")
public class StockIncome extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "stock_id", nullable = false)
	private Stock stock;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "purchase_bill_id")
	private PurchaseBill purchaseBill;

	@Column(name = "quantity", nullable = false)
	private BigDecimal quantity;

	@Column(name = "available_quantity", nullable = false)
	private BigDecimal availableQuantity;

	@Column(name = "unit_buying_price", nullable = false)
	private BigDecimal unitBuyingPrice;

	@Column(name = "total_buying_amount", nullable = false)
	private BigDecimal totalBuyingAmount;
}
