package com.example.demoMultipleDataBaseConfiguration.Service;

import com.example.demoMultipleDataBaseConfiguration.Model.employee.Employee;
import com.example.demoMultipleDataBaseConfiguration.Model.user.User;
import com.example.demoMultipleDataBaseConfiguration.Repository.employee.EmployeeRepo;
import com.example.demoMultipleDataBaseConfiguration.Repository.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MultipleDataBaseService {

    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private UserRepo userRepo;

    public Employee empDataStore(Employee employee) {
        return employeeRepo.save(employee);
    }

    public User userDataStore(User user) {
        return userRepo.save(user);
    }

    public List<Employee> allEmpDataShow() {
        return employeeRepo.findAll();
    }

    public List<User> allUserDataShow() {
        return userRepo.findAll();
    }

    public User findByEmail(String userEmail) {
        return userRepo.findByEmail(userEmail);
    }

    public List<Employee> specificEmployeeShow(String empName) {
        return employeeRepo.findByName(empName);
    }

    public List<User> specificUserShow(String userName) {
        return userRepo.findByName(userName);
    }
}

