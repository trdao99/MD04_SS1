package com.ra.md04_ss1_baitap.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "DepartName")
    private String DepartName;
    @Column(name = "Status", nullable = true)
    private Boolean Status;
    @OneToMany(mappedBy = "department")
    private List<Employee> employees;
}
