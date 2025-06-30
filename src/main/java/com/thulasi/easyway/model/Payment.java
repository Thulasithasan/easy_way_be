package com.thulasi.easyway.model;

import java.math.BigDecimal;

import com.thulasi.easyway.type.PaymentMethod;
import com.thulasi.easyway.type.PaymentStatus;
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
@Table(name = "payments")
public class Payment extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@Column(name = "payment_ref_no", nullable = false)
	private String paymentRefNo = IdGenerator.generateId("PAY");

	@ManyToOne
	@JoinColumn(name = "invoice_id", nullable = false)
	private Invoice invoice;

	@Column(name = "payment_amount", nullable = false)
	private BigDecimal paymentAmount;

	@Column(name = "payment_method", nullable = false)
	@Enumerated(EnumType.STRING)
	private PaymentMethod paymentMethod;

	@Column(name = "payment_status", nullable = false)
	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus;

	@PrePersist
	public void prePersist() {
		if (this.paymentRefNo == null) {
			this.paymentRefNo = IdGenerator.generateId("PAY");
		}
	}
}
