package com.ra.md04_ss1_baitap.controller;

import com.ra.md04_ss1_baitap.entity.Department;
import com.ra.md04_ss1_baitap.service.impl.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/department")
public class DepartmentController {
    @Autowired private DepartmentService departmentService;
    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("list", departmentService.getAll());
        return "indexDepartment";
    }

    @GetMapping("/addinit")
    public String addInit(Model model) {
        model.addAttribute("department", new Department());
        return "addDepartment";
    }
    @GetMapping("/editinit/{id}")
    public String editInit(Model model, @PathVariable int id) {
        model.addAttribute("department", departmentService.findById(id));
        return "editDepartment";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Department department) {
        departmentService.add(department);
        return "redirect:/department";
    }
    @PostMapping("/edit/{id}")
    public String edit(@ModelAttribute Department department, @PathVariable int id) {
        department.setId(id);
        departmentService.update(department);
        return "redirect:/department";
    }
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        departmentService.deleteById(id);
        return "redirect:/department";
    }
}
