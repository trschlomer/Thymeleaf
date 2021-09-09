package com.luv2code.springboot.thymeleafdemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luv2code.springboot.thymeleafdemo.dao.EmployeeRepository;
import com.luv2code.springboot.thymeleafdemo.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	//no need to put @transactional over methods as jparepository handles this
	@Autowired
	private EmployeeRepository empRep;
	
	
	public EmployeeServiceImpl(EmployeeRepository theEmpRep) {
		empRep = theEmpRep;
	}

	
	public List<Employee> findAll() {
		return empRep.findAllByOrderByLastNameAsc();
	}

	
	public Employee findById(int theId) {
		//dont have to explicitly check for null
		Optional<Employee> result = empRep.findById(theId);
		
		Employee theEmployee = null;
		
		if(result.isPresent()) {
			theEmployee = result.get();
		}
		else {
			//emp not found
			throw new RuntimeException("Didn't find employee id - " +theId);
		}
		return theEmployee;
	}

	
	public void save(Employee theEmp) {
		empRep.save(theEmp);
	}

	
	public void deleteById(int theId) {
		empRep.deleteById(theId);

	}

}
