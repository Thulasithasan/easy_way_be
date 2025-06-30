package com.thulasi.easyway.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.thulasi.easyway.model.Stock;

import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    Optional<Stock> findByProductId(Long productId);

    @Query(value = "SELECT DISTINCT s.* FROM stocks s " +
            "JOIN products p ON s.product_id = p.id " +
            "JOIN product_names pn ON p.id = pn.product_id " +
            "JOIN sub_categories sc ON p.sub_category_id = sc.id " +
            "JOIN categories c ON sc.category_id = c.id " +
            "WHERE (:categoryId IS NULL OR c.id = :categoryId) " +
            "AND (:subCategoryId IS NULL OR sc.id = :subCategoryId) " +
            "AND (:productName IS NULL OR LOWER(pn.name) LIKE LOWER(CAST(:productName AS text)))",
            nativeQuery = true)
    Page<Stock> filterStocks(
            @Param("categoryId") Long categoryId,
            @Param("subCategoryId") Long subCategoryId,
            @Param("productName") String productName,
            Pageable pageable
    );



}
