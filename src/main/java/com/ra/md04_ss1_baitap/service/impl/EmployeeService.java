package com.ra.md04_ss1_baitap.service.impl;

import com.ra.md04_ss1_baitap.entity.Employee;
import com.ra.md04_ss1_baitap.repository.IEmployeeReporsitory;
import com.ra.md04_ss1_baitap.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EmployeeService implements IEmployeeService {
    @Autowired
    IEmployeeReporsitory employeeReporsitory;

    @Override
    public List<Employee> getProducts() {
        return employeeReporsitory.findAll();
    }

    @Override
    public Employee getProductById(Integer employeeID) {
        return employeeReporsitory.findById(employeeID).orElseThrow(() -> new NoSuchElementException("Khong ton tai san pham to ma " + employeeID));
    }

    @Override
    public Employee insertProduct(Employee product) {
        return employeeReporsitory.save(product);
    }

    @Override
    public Employee updateProduct(Employee product) {
        employeeReporsitory.findById(product.getId()).orElseThrow(() -> new NoSuchElementException("Khong ton tai san pham nay trong database"));
        return employeeReporsitory.save(product);
    }

    @Override
    public void deleteProduct(Integer proId) {
        employeeReporsitory.deleteById(proId);
    }

    @Override
    public List<Employee> getProductByName(String employeeName) {
        return employeeReporsitory.findByEmployeeName(employeeName);
    }
}
