package com.thulasi.easyway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thulasi.easyway.model.StockIncome;

@Repository
public interface StockIncomeRepository extends JpaRepository<StockIncome, Long> {

}
