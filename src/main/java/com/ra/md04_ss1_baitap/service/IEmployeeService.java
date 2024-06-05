package com.ra.md04_ss1_baitap.service;

import com.ra.md04_ss1_baitap.entity.Employee;

import java.util.List;

public interface IEmployeeService {
    List<Employee> getProducts();
    Employee getProductById(Integer employeeID);
    Employee insertProduct(Employee employee);
    Employee updateProduct(Employee employee);
    void deleteProduct(Integer proId);
    List<Employee> getProductByName(String employeeName);
}
