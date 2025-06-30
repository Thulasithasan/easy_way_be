package com.thulasi.easyway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thulasi.easyway.model.SalesOrderItem;

@Repository
public interface SalesOrderItemRepository extends JpaRepository<SalesOrderItem, Long> {

}
