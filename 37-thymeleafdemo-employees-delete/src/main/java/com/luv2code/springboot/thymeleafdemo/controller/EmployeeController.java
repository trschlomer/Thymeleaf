package com.luv2code.springboot.thymeleafdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	private EmployeeService empServ;
	
	@Autowired
	public EmployeeController(EmployeeService theEmployeeService) {
		empServ = theEmployeeService;
	}
	//add mapping for "/list"
	@GetMapping("list")
	public String listEmployees(Model theModel) {
		
		List<Employee> theEmployees = empServ.findAll();
		theModel.addAttribute("employees", theEmployees);
		
		return "employees/list-employees";
	}
	
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		//model attribute bind new emp
		Employee emp = new Employee();
		
		theModel.addAttribute("employee", emp);
		
		return "employees/employee-form";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel) {
		
		//get emp
		Employee emp = empServ.findById(theId);
		//sey emp as model attr to pre pop
		theModel.addAttribute("employee",emp);
		//send to form
		return "employees/employee-form";
	}
	
	
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {
		
		//save
		empServ.save(theEmployee);
		//redirect to prevent duplicate submissions
		return "redirect:/employees/list";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int theId) {
		
		//delete
		empServ.deleteById(theId);
		//redirect to /employees/list
		return "redirect:/employees/list";
	}
	
}
