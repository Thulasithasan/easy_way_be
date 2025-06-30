package com.thulasi.easyway.repository;

import com.thulasi.easyway.model.CardItem;
import com.thulasi.easyway.model.RecurringOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecurringOrderRepository extends JpaRepository<RecurringOrder, Long> {

    List<RecurringOrder> findByUserId(Long userId);
}
