package com.ra.md04_ss1_baitap.repository;

import com.ra.md04_ss1_baitap.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IEmployeeReporsitory extends JpaRepository<Employee, Integer> {
    List<Employee> findByEmployeeName(String employeeName);
}
