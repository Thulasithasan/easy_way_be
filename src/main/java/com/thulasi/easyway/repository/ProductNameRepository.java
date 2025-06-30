package com.thulasi.easyway.repository;

import com.thulasi.easyway.model.Branch;
import com.thulasi.easyway.model.ProductName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductNameRepository extends JpaRepository<ProductName, Long> {

}
