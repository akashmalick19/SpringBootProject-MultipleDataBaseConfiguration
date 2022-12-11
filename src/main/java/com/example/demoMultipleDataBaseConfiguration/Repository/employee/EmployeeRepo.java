package com.example.demoMultipleDataBaseConfiguration.Repository.employee;

import com.example.demoMultipleDataBaseConfiguration.Model.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Long> {
    @Query(value = "select * from employee e where e.empName= ?1",
    nativeQuery = true
    )
    List<Employee> findByName(String empName);

}
