package com.Employee.CrudDemo.dao;

import com.Employee.CrudDemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RestController;

//@RepositoryRestResource(path="members")
// this is used to change our endpoints from
//http://localhost:8080/api/employees  ---- >>>  http://localhost:8080/api/members


public interface EmployeeRespository extends JpaRepository<Employee, Integer> {


//    public interface EmployeeRespository extends JpaRepository<Employee, Integer>
//      JpaRespository <Employee - entity type , Integer - in our entity class our id is integer>


}
