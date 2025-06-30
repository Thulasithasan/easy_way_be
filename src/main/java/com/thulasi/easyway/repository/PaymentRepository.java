package com.thulasi.easyway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thulasi.easyway.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
