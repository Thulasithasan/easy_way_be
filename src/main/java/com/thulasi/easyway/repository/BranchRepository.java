package com.thulasi.easyway.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thulasi.easyway.model.Branch;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {

}
