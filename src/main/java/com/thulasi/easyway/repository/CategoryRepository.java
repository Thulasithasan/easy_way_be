package com.thulasi.easyway.repository;

import com.thulasi.easyway.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	Optional<Category> findByName(String name);

	boolean existsByNameIgnoreCase(String name);

	boolean existsByNameIgnoreCaseAndIdNot(String name, Long id);

	@Query("SELECT c FROM Category c " + "WHERE (LOWER(c.name) = LOWER(:categoryName) OR :categoryName IS NULL) "
			+ "AND (c.isActive = :isActive OR :isActive IS NULL )"
			+ "AND (LOWER(c.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR "
			+ "LOWER(c.description) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR :searchTerm IS NULL)")
	Page<Category> filterCategories(@Param("categoryName") String categoryName, @Param("searchTerm") String searchTerm,
			@Param("isActive") Boolean isActive, Pageable pageable);

}