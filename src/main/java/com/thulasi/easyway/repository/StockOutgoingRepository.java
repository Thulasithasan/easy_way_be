package com.thulasi.easyway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thulasi.easyway.model.StockOutgoing;

@Repository
public interface StockOutgoingRepository extends JpaRepository<StockOutgoing, Long> {

}
