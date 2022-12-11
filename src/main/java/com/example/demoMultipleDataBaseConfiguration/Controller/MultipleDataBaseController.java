package com.example.demoMultipleDataBaseConfiguration.Controller;

import com.example.demoMultipleDataBaseConfiguration.Model.employee.Employee;
import com.example.demoMultipleDataBaseConfiguration.Model.user.User;
import com.example.demoMultipleDataBaseConfiguration.Service.MultipleDataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
public class MultipleDataBaseController {

    @Autowired
    private MultipleDataBaseService multipleDataBaseService;


    @PostMapping("/empStore")
    public Map<String,Object> empDataStore(@RequestBody Employee employee) throws Exception{
        Map<String, Object> map = new HashMap<>();
        try{
            if (employee.getEmpName().isEmpty() || employee.getEmpName().equals(" ")){
                throw new NullPointerException("Employee name should not be null or space input.");
            }
            if(employee.getEmpAge() <= 0) {
                throw new NumberFormatException("Employee age should not be null or should not Less equal to zero.");
            }
        }
        catch(Exception e) {
            map.put("STATUS: ", HttpStatus.NOT_ACCEPTABLE);
            map.put("MESSAGE: ", e.getMessage());
            map.put("OBJECT: ",employee);
            return map;
        }
        Employee employee1 = multipleDataBaseService.empDataStore(employee);
        map.put("STATUS: ", HttpStatus.ACCEPTED);
        map.put("MESSAGE: ", "Employee details are store inside Employee Data Base.");
        map.put("OBJECT: ", employee1);
        return map;
    }


    @PostMapping("/userStore")
    public Map<String,Object> userDataStore(@RequestBody User user){
        Map<String,Object> map=new HashMap<>();
        try{
            if(user.getUserName().isEmpty()|| user.getUserName().equals(" ")){
                throw new NullPointerException("User name should be null or should not start with space character.");
            }
            if(user.getUserEmail().isEmpty()){
                throw new NullPointerException("User email should be null.");
            }
            if(user.getUserPassword().isEmpty()){
                throw new NullPointerException("User password should be null.");
            }
        }
        catch (Exception e){
            map.put("STATUS: ", HttpStatus.NOT_ACCEPTABLE);
            map.put("MESSAGE: ",e.getMessage());
            map.put("OBJECT: ",user);
            return map;
        }
        User user2=multipleDataBaseService.findByEmail(user.getUserEmail());
        try{
            if(user2!=null){
                throw new NoSuchFieldException("Same User EmailId is already present inside Data Base, please put new EmailId.");
            }
            User user1=multipleDataBaseService.userDataStore(user);
            map.put("STATUS: ", HttpStatus.ACCEPTED);
            map.put("MESSAGE: ","User details are store inside User Data Base.");
            map.put("OBJECT: ",user1);
            return map;
        }
        catch (Exception e1){
            map.put("STATUS: ", HttpStatus.NOT_ACCEPTABLE);
            map.put("MESSAGE: ",e1.getMessage());
            map.put("OBJECT: ",user.getUserEmail());
            return map;
        }
    }


    @GetMapping("/allEmpShow")
    public Map<String,Object> allEmpDataShow(){
        Map<String,Object> map=new HashMap<>();
        List<Employee> employeeList=multipleDataBaseService.allEmpDataShow();
        if(employeeList.isEmpty()){
            map.put("STATUS: ",HttpStatus.NO_CONTENT);
            map.put("MESSAGE: ","Employee Data Base is Empty.");
            map.put("OBJECTS: ",employeeList);
            return map;
        }
        map.put("STATUS: ",HttpStatus.OK);
        map.put("MESSAGE: ","List of Employees details are shown from Employee Data Base.");
        map.put("OBJECTS: ",employeeList);
        return map;
    }


    @GetMapping("/allUserShow")
    public Map<String,Object> allUserDataShow(){
        Map<String,Object> map=new HashMap<>();
        List<User> userList=multipleDataBaseService.allUserDataShow();
        if(userList.isEmpty()){
            map.put("STATUS: ",HttpStatus.NO_CONTENT);
            map.put("MESSAGE: ","User Data Base is Empty.");
            map.put("OBJECTS: ",userList);
            return map;
        }
        map.put("STATUS: ",HttpStatus.OK);
        map.put("MESSAGE: ","List of Users details are shown from User Data Base.");
        map.put("OBJECTS: ",userList);
        return map;
    }


    @GetMapping("/specificEmpShow/{empName}")
    public Map<String,Object> specificEmployeeShow(@PathVariable("empName") String empName){
        Map<String,Object> map=new HashMap<>();
        List<Employee> employeeList=multipleDataBaseService.specificEmployeeShow(empName);
        try{
            if(employeeList.isEmpty()){
                throw new NoSuchElementException(empName + " name is not present Employee Data Base table.");
            }
            map.put("STATUS : ",HttpStatus.OK);
            map.put("MESSAGE : ","All specific Data are found inside Employee Data Base table.");
            map.put("OBJECTS : ",employeeList);
            return map;
        }
        catch(Exception e){
            map.put("STATUS : ",HttpStatus.NOT_FOUND);
            map.put("MESSAGE : ",e.getMessage());
            map.put("OBJECTS : ",employeeList);
            return map;
        }
    }


    @GetMapping("/specificUserShow/{userName}")
    public Map<String,Object> specificUserShow(@PathVariable("userName") String userName){
        Map<String,Object> map=new HashMap<>();
        List<User> userList=multipleDataBaseService.specificUserShow(userName);
        try{
            if(userList.isEmpty()){
                throw new NoSuchElementException(userName + " name is not present inside User Data Base table.");
            }
            map.put("STATUS : ",HttpStatus.OK);
            map.put("MESSAGE : ","All specific Data are found User Data Base table.");
            map.put("OBJECTS : ",userList);
            return map;
        }
        catch(Exception e){
            map.put("STATUS : ",HttpStatus.NOT_FOUND);
            map.put("MESSAGE : ",e.getMessage());
            map.put("OBJECTS : ",userList);
            return map;
        }
    }

}

