package poc.springboot.controller;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import poc.springboot.bean.AuthenticationBean;
import poc.springboot.exception.ResourceNotFoundException;
import poc.springboot.model.Employee;
import poc.springboot.repository.EmployeeRepository;
import poc.springboot.service.EmployeeService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/")
public class EmployeeController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private EmployeeService employeeService;
	
	
	// get all employees
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		logger.info(".....Getting all employees.....");
		return employeeService.getEmployees();
	}		
	
	
	
	// create employee rest api
	@PostMapping("/employees")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
		logger.info(".....Adding Employee.....");
		employeeService.addEmployee(employee);
		return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
	}
	
	
	
	// get employee by id rest api
	@RequestMapping("/employees/{id}")
	public Optional<Employee> getEmployee(@PathVariable Long id) {
		logger.info(".....Getting employees by their id.....");
		return employeeService.getEmployee(id);
	}
	
	
	//
	// update employee rest api
	@PutMapping(value = "/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,@RequestBody Employee employee) {
		logger.info(".....Updating employee by id.....");
		employeeService.updateEmployee(id,employee);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}
	
	
	// delete employee rest api
	@DeleteMapping(value = "/employees/{id}")			
	public String deleteEmployee(@PathVariable Long id) {
		logger.info(".....Deleting employee by id.....");
		employeeService.deleteEmployee(id);
		return "Employee deleted with id:"+id;
	}
	
	
	 @GetMapping(path = "/basicauth")
	    public AuthenticationBean basicauth() {
	        return new AuthenticationBean("You are authenticated");
	    }
	
	
	 //  get employee by firstName rest api
	 @GetMapping(value = "/employees/findName/{firstName}")
		public List<Employee> findByFirstName(@PathVariable String firstName) {
			logger.info(".....Searching user by first name.....");
			return employeeService.findByFirstName(firstName);
		}
}
