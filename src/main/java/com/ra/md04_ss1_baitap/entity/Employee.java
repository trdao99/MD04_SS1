package com.ra.md04_ss1_baitap.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Employee")
public class Employee {
    @Id
    @Column(name = "EmployeeID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "EmployeeName")
    private String employeeName;
    @Column(name = "Gender")
    private Boolean gender;
    @Column(name = "BirthDay")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDay;
    @Column(name = "Position")
    private String position;
    @Column(name = "salary")
    private Double salary;
    @Column(name = "img")
    private String img;
    @ManyToOne
    @JoinColumn(name = "DepartmentId",referencedColumnName = "id")
    private Department department;
}
