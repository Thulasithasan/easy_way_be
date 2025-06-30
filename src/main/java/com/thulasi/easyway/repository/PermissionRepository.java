package com.thulasi.easyway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thulasi.easyway.model.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

}
