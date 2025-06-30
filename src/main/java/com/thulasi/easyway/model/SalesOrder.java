package com.thulasi.easyway.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.thulasi.easyway.type.SalesOrderStatus;
import com.thulasi.easyway.type.SalesOrderType;

import com.thulasi.easyway.util.IdGenerator;
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
@Table(name = "sales_orders")
public class SalesOrder extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@Column(name = "order_ref_no", nullable = false)
	private String orderRefNo = IdGenerator.generateId("ORD");

	@OneToMany(mappedBy = "salesOrder", cascade = CascadeType.ALL, orphanRemoval = true)
	@Builder.Default
	private List<SalesOrderItem> salesOrderItems = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private User customer;

	@ManyToOne
	@JoinColumn(name = "sales_person_id")
	private User salesPerson;

	@ManyToOne
	@JoinColumn(name = "delivery_person_id")
	private User deliveryPerson;

	@Column(name = "total_buying_amount", nullable = false)
	private BigDecimal totalBuyingAmount;

	@Column(name = "total_selling_amount", nullable = false)
	private BigDecimal totalSellingAmount;

	@Column(name = "total_profit_amount", nullable = false)
	private BigDecimal totalProfitAmount;

	@Column(name = "total_quantity")
	private BigDecimal totalQuantity;

	@Column(name = "total_weight")
	private BigDecimal totalWeight;

	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	private SalesOrderStatus status;

	@Column(name = "type", nullable = false)
	@Enumerated(EnumType.STRING)
	private SalesOrderType type;

	@PrePersist
	public void prePersist() {
		if (this.orderRefNo == null) {
			this.orderRefNo = IdGenerator.generateId("ORD");
		}
	}
}
