package com.thulasi.easyway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thulasi.easyway.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("SELECT DISTINCT p FROM Product p " +
			"JOIN p.nameTranslations pn " +
			"JOIN p.subCategory sc " +
			"JOIN sc.category c " +
			"WHERE (:productName IS NULL OR LOWER(pn.name) LIKE LOWER(CONCAT('%', :productName, '%'))) " +
			"AND (:subCategoryId IS NULL OR sc.id = :subCategoryId) " +
			"AND (:categoryId IS NULL OR c.id = :categoryId)")
	Page<Product> filterProducts(
			@Param("productName") String productName,
			@Param("subCategoryId") Long subCategoryId,
			@Param("categoryId") Long categoryId,
			Pageable pageable);

}
