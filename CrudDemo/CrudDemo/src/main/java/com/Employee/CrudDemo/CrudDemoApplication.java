package com.Employee.CrudDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrudDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudDemoApplication.class, args);

		// we have done the changes in application properties so that we can hit our api like - http://localhost:8080/api/employees
		// if not then commit that change in application properties and then hit the url like http://localhost:8080/employees

	}

}
