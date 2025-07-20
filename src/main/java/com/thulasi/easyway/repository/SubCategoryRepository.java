package com.thulasi.easyway.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.thulasi.easyway.model.SubCategory;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {

    @Query("SELECT c FROM SubCategory c " +
            "WHERE (LOWER(c.name) = LOWER(:subCategoryName) OR :subCategoryName IS NULL)")
    Page<SubCategory> filterSubCategories(@Param("subCategoryName") String subCategoryName, Pageable pageable);

}
