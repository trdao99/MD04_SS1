package com.ra.md04_ss1_baitap.repository;

import com.ra.md04_ss1_baitap.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDepartmentRepository extends JpaRepository<Department, Integer> {
}
