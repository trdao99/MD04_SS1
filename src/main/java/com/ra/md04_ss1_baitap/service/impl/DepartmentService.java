package com.ra.md04_ss1_baitap.service.impl;

import com.ra.md04_ss1_baitap.entity.Department;
import com.ra.md04_ss1_baitap.repository.IDepartmentRepository;
import com.ra.md04_ss1_baitap.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DepartmentService implements IDepartmentService {
    @Autowired
    IDepartmentRepository departmentRepository;
    @Override
    public Department findById(int id) {
     return departmentRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Khong ton tai san pham nay trong database"));

    }

    @Override
    public void add(Department department) {
        departmentRepository.save(department);
    }

    @Override
    public Department update(Department department) {
        departmentRepository.findById(department.getId()).orElseThrow(() -> new NoSuchElementException("Khong ton tai san pham nay trong database"));
        return departmentRepository.save(department);
    }


    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    public void deleteById(int id) {
        departmentRepository.deleteById(id);
    }
}
