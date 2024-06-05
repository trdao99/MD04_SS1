package com.ra.md04_ss1_baitap.service;

import com.ra.md04_ss1_baitap.entity.Department;

import java.util.List;

public interface IDepartmentService {
    List<Department> getAll();
    Department findById(int id);
    void add(Department department);
    Department update(Department department);
}
