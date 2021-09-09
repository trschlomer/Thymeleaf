package com.luv2code.springboot.thymeleafdemo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	//sort by last name
	//find all by, order by last name, asc automatically does what method describes
	//query methods spring data jpa
	public List<Employee> findAllByOrderByLastNameAsc();
}
