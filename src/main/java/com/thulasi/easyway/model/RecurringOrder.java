package com.thulasi.easyway.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "recurring_orders")
public class RecurringOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "note")
    private String note;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @OneToMany(mappedBy = "recurringOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecurringOrderItem> items = new ArrayList<>();
}
