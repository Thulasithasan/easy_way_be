package com.thulasi.easyway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.thulasi.easyway.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c " +
            "WHERE (LOWER(c.name) = LOWER(:categoryName) OR :categoryName IS NULL)")
    Page<Category> filterCategories(@Param("categoryName") String categoryName, Pageable pageable);



}
