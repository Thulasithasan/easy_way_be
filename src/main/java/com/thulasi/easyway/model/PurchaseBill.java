package com.thulasi.easyway.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "purchase_bills")
public class PurchaseBill extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    // The anchor/supplier company that issued the bill
    @Column(name = "supplier_name", nullable = false)
    private String supplierName;

    @Column(name = "bill_number", nullable = false, unique = true)
    private String billNumber;

    @Column(name = "bill_date", nullable = false)
    private LocalDate billDate;

    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalAmount;

    @Column(name = "bill_image_url")
    private String billImageUrl;

    @OneToMany(mappedBy = "purchaseBill", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<StockIncome> stockIncomes = new ArrayList<>();

    @Column(name = "note")
    private String note;
}

