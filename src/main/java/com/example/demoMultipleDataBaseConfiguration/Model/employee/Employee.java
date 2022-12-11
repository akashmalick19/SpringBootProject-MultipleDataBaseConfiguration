package com.example.demoMultipleDataBaseConfiguration.Model.employee;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empId;
    @NotNull
    private String empName;
    @NotNull
    private Integer empAge;

    public Employee(String empName, Integer empAge) {
        this.empName = empName;
        this.empAge = empAge;
    }
}

