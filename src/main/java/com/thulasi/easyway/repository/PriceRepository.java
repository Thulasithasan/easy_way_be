package com.thulasi.easyway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thulasi.easyway.model.Price;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

}
