package poc.springboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import poc.springboot.repository.EmployeeRepository;
import poc.springboot.exception.ResourceNotFoundException;
import poc.springboot.model.Employee;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public List<Employee> getEmployees() {
		List<Employee> employees = new ArrayList<Employee>();
		employeeRepository.findAll().forEach(employees::add);
		return employees;
	}
	
	public Optional<Employee> getEmployee(Long id) {
		return employeeRepository.findById(id);
	}
	
	public Long addEmployee(Employee employee) {
		 employeeRepository.save(employee);
		 return employee.getId();
	}
	
	public String deleteEmployee(Long id) {
		employeeRepository.deleteById(id);
		return "Employee deleted with id:"+id;
	}
	
	
	public Employee updateEmployee(Long id,Employee employee) {	//??
		return employeeRepository.findById(id).map(u->{
			u.setFirstName(employee.getFirstName());
			u.setLastName(employee.getLastName());
			u.setEmailId(employee.getEmailId());
			u.setDepartment(employee.getDepartment());
			u.setCity(employee.getCity());
			return employeeRepository.save(u);
		}).orElseThrow(()->new ResourceNotFoundException("Id"+id+"not found"));
	}

	
	public List<Employee> findByFirstName(String firstName) {
		return employeeRepository.findByFirstName(firstName);
	}
	
	
}

