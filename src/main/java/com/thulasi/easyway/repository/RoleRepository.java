package com.thulasi.easyway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thulasi.easyway.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
