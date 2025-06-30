package com.thulasi.easyway.repository;

import com.thulasi.easyway.model.CardItem;
import com.thulasi.easyway.model.RecurringOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecurringOrderItemRepository extends JpaRepository<RecurringOrderItem, Long> {

    Optional<RecurringOrderItem> findByProductIdAndRecurringOrderId(Long productId, Long recurringOrderId);
}
