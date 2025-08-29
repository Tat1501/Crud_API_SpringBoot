package com.Employee.CrudDemo.service;

//import com.Employee.CrudDemo.dao.EmployeeDAO;
import com.Employee.CrudDemo.dao.EmployeeRespository;
import com.Employee.CrudDemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRespository employeeRepsository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRespository theEmployeeRepsository){

        employeeRepsository = theEmployeeRepsository;
    }

    @Override
    public List<Employee> findAll() {

        return employeeRepsository.findAll();
    }

    @Override
    public Employee findById(int id) {
        Optional<Employee> result = employeeRepsository.findById(id);

        Employee theEmployee = null;
        if(result.isPresent()){
            theEmployee = result.get();
        }
        else{
            throw new RuntimeException("Did Not find employee Id - "+ id);
        }
        return theEmployee;
    }

    @Override
    public Employee save(Employee theEmployee) {

        return employeeRepsository.save(theEmployee);
    }

    @Override
    public void deleteById(int id) {

        employeeRepsository.deleteById(id);
    }
}
