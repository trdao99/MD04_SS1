package com.ra.md04_ss1_baitap.controller;

import com.ra.md04_ss1_baitap.entity.Department;
import com.ra.md04_ss1_baitap.entity.Employee;
import com.ra.md04_ss1_baitap.service.UploadFile;
import com.ra.md04_ss1_baitap.service.impl.DepartmentService;
import com.ra.md04_ss1_baitap.service.impl.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping({"/employee", "/"})
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    UploadFile uploadFile;

    @GetMapping
    public String index(Model model) {
        List<Employee> list = employeeService.getProducts();
        model.addAttribute("list", list);
        return "index";
    }

    @GetMapping("/addinit")
    public String addProduct(Model model) {
        List<Department> listCate = departmentService.getAll();
        Employee p = new Employee();
        model.addAttribute("listCate", listCate);
        model.addAttribute("p", p);
        return "add";
    }

    @GetMapping("/editinit/{id}")
    public String editProduct(Model model, @PathVariable int id, @ModelAttribute Employee p) {
        p = employeeService.getProductById(id);
        model.addAttribute("imgFile", p.getImg());
        List<Department> listCate = departmentService.getAll();
        model.addAttribute("listCate", listCate);
        model.addAttribute("p", p);
        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@ModelAttribute Employee employee, @RequestParam("department") Integer departmentId, @PathVariable("id") Integer id,@RequestParam MultipartFile imgFile) {
        if(imgFile.isEmpty()) {
            String img = uploadFile.uploadToLocal(imgFile);
            employee.setImg(img);
        }else{
            String img = uploadFile.uploadToLocal(imgFile);
            employee.setImg(img);
        }
        employee.setId(id);
        employee.setDepartment(departmentService.findById(departmentId));
        employeeService.updateProduct(employee);
        return "redirect:/employee";
    }

    @PostMapping("/add")
    public String saveProduct(@ModelAttribute Employee employee, Model model, @RequestParam MultipartFile imgFile) {
        if (!imgFile.isEmpty()) {
            String img = uploadFile.uploadToLocal(imgFile);
            employee.setImg(img);
        }
        Employee p = employeeService.insertProduct(employee);
        if (p != null) {
            return "redirect:/employee";
        } else {
            List<Department> listCate = departmentService.getAll();
            model.addAttribute("listCate", listCate);
            model.addAttribute("p", p);
            return "add";
        }
    }

    @RequestMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Integer id) {
        employeeService.deleteProduct(id);
        return "redirect:/employee";
    }
}
