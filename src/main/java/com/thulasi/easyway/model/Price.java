package com.thulasi.easyway.model;

import java.math.BigDecimal;

import com.thulasi.easyway.type.MeasurementUnit;

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
@Table(name = "prices")
public class Price extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "stock_id", nullable = false)
	private Stock stock;

	@Column(name = "quantity", nullable = false)
	private BigDecimal quantity;

	@Enumerated(EnumType.STRING)
	@Column(name = "unit", nullable = false)
	private MeasurementUnit unit;

	@Column(name = "actual_price", nullable = false)
	private BigDecimal actualPrice;

	@Column(name = "discount_percent", nullable = false)
	private BigDecimal discountPercent;

	@Column(name = "discount_amount", nullable = false)
	private BigDecimal discountAmount;

	@Column(name = "final_price", nullable = false)
	private BigDecimal finalPrice;

	@Builder.Default
	@Column(name = "is_active", nullable = false)
	private Boolean isActive = true;

	@Builder.Default
	@Column(name = "is_default", nullable = false)
	private Boolean isDefault = false;
}
