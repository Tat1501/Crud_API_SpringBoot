package com.Employee.CrudDemo.dao;

import com.Employee.CrudDemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRespository extends JpaRepository<Employee, Integer> {


//    public interface EmployeeRespository extends JpaRepository<Employee, Integer>
//      JpaRespository <Employee - entity type , Integer - in our entity class our id is integer>


}
