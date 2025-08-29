package com.Employee.CrudDemo.restController;

//import com.Employee.CrudDemo.dao.EmployeeDAO;
import com.Employee.CrudDemo.entity.Employee;
import com.Employee.CrudDemo.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    private ObjectMapper objectMapper;


    // inject employee DAO directly
    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService, ObjectMapper theObjectMapper){
        employeeService = theEmployeeService;
        objectMapper = theObjectMapper;
    }


    // expose "/employee" and return the list of employee
    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }


    // add mapping for get /employee/{employeesId}
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId){
        Employee theEmployee = employeeService.findById(employeeId);

        if(theEmployee == null){
            throw new RuntimeException("Employee id not Found - "+ employeeId);
        }
        return theEmployee;
    }


    // add mapping for Post /employees - add a new employee

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee){

        theEmployee.setId(0);
        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }


    // add mapping for PUT /employee/{employeeId} - update the employee
    @PutMapping("/employees")
    public Employee UpdateEmployee(@RequestBody Employee theEmployee){

        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }



    // add mapping for DELETE /employee/{employeeId} - delete employee
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){

        Employee theEmployee = employeeService.findById(employeeId);

        if(theEmployee == null){
            throw new RuntimeException("The Employee Did not exist - "+employeeId);
        }
        employeeService.deleteById(employeeId);
        return "Employee has been Deleted = "+employeeId;
    }


    // add mapping for Patch /employee/{employeeId} - partial update
    @PatchMapping("/employees/{employeeId}")
    public Employee patchEmployee(@PathVariable int employeeId, @RequestBody Map<String, Object> patchPayLoad){

        // reterive the emp id from db
        Employee tempEmployee = employeeService.findById(employeeId);

        // throw exception if null
        if(tempEmployee == null){
            throw new RuntimeException("Employee Id not Found "+ employeeId);
        }

        // throw exception if request body also contains id
        if(patchPayLoad.containsKey("id")){
            throw new RuntimeException("Employee Id is not in the request body "+ employeeId);
        }

        Employee patchEmployee = apply(patchPayLoad, tempEmployee);

        Employee dbEmployee = employeeService.save(patchEmployee);

        return dbEmployee;
    }



    private Employee apply(Map<String, Object> patchPayLoad, Employee tempEmployee) {

        // convert employee object to a json object node
        ObjectNode employeeNode = objectMapper.convertValue(tempEmployee,ObjectNode.class);

        // convert the patchPayLoad map to a json object node
        ObjectNode patchNode = objectMapper.convertValue(patchPayLoad,ObjectNode.class);

        // merge the patch updates into the employee node
        employeeNode.setAll(patchNode);

        return objectMapper.convertValue(employeeNode,Employee.class);
    }
}
