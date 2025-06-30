package com.thulasi.easyway.model;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

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
@Table(name = "invoices")
public class Invoice extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "invoice_ref_no", nullable = false)
    private String invoiceRefNo = IdGenerator.generateId("INV");

    @OneToOne
    @JoinColumn(name = "sales_order_id", nullable = false)
    private SalesOrder salesOrder;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Payment> payments = new ArrayList<>();

    @Column(name = "invoice_amount", nullable = false)
    private BigDecimal invoiceAmount;

    @PrePersist
    public void prePersist() {
        if (this.invoiceRefNo == null) {
            this.invoiceRefNo = IdGenerator.generateId("INV");
        }
    }
}
