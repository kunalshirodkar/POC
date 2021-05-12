package poc.springboot.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import poc.springboot.controller.EmployeeController;
import poc.springboot.model.Employee;
import poc.springboot.repository.EmployeeRepository;
import poc.springboot.service.EmployeeService;


//@SpringBootTest(classes=EmployeeController.class)
@WebMvcTest(value=EmployeeController.class, secure=false)
public class EmployeeControllerTest {		
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private EmployeeRepository repo;
	
	@MockBean
	private EmployeeService service;
	
	ObjectMapper om = new ObjectMapper();
	
	public static List<Employee> setUp() {
		Employee employee1 = new Employee();
		employee1.setId(1L);
		employee1.setFirstName("kunal");
		employee1.setLastName("shirodkar");
		employee1.setEmailId("ks@gmail.com");
		employee1.setDepartment("Java");
		employee1.setCity("mumbai");

		
		Employee employee2 = new Employee();
		employee2.setId(2L);
		employee2.setFirstName("priti");
		employee2.setLastName("bhutelo");
		employee2.setEmailId("pb@gmail.com");
		employee2.setDepartment("Web");
		employee2.setCity("kolhapur");

	
		List<Employee> listEmployee = new ArrayList<Employee>();
		listEmployee.add(employee1);
		listEmployee.add(employee2);
		
		return listEmployee;
	}

	
	@Test
	public void addEmployeeTest() throws Exception {               
		Mockito.when(service.addEmployee(EmployeeControllerTest.setUp().get(0))).thenReturn(1L);    
		String payload = om.writeValueAsString(EmployeeControllerTest.setUp().get(0));        
		MvcResult result = mockMvc
				.perform(post("/employees").content(payload).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isCreated()).andReturn();
		int status = result.getResponse().getStatus();
		assertEquals(201, status);
	
	}
	
	
	@Test
	public void getAllEmployeesTest() throws Exception {         
		List<Employee> lst = EmployeeControllerTest.setUp();
		String response = om.writeValueAsString(lst);
		Mockito.when(service.getEmployees()).thenReturn(lst);
		MvcResult result = mockMvc.perform(get("/employees")
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		String employeeRes = result.getResponse().getContentAsString();
		assertEquals(response, employeeRes);
				
	}
	
	
	
	@Test
	public void updateEmployeeTest() throws Exception {
		Mockito.when(service.updateEmployee(EmployeeControllerTest.setUp().get(1).getId(),EmployeeControllerTest.setUp().get(1)))
		.thenReturn(EmployeeControllerTest.setUp().get(1));
		String payload = om.writeValueAsString(EmployeeControllerTest.setUp().get(1));
		MvcResult result = mockMvc
				.perform(put("/employees/1").content(payload).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		int status = result.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	
	@Test
	public void getEmployeebyIdTest() throws Exception {
		String response=om.writeValueAsString(EmployeeControllerTest.setUp().get(0));
		Mockito.when(service.getEmployee(1L)).thenReturn(java.util.Optional.of(EmployeeControllerTest.setUp().get(0)));
		MvcResult result = mockMvc.perform(get("/employees/1").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		int response1 = result.getResponse().getStatus();
		assertEquals(200, response1);
		
	}
	
	
	
	
	@Test
	public void deleteEmployeeTest() throws Exception {
		
		MvcResult result = mockMvc.perform(delete("/employees/1").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		int response = result.getResponse().getStatus();
		assertEquals(200, response);
		assertEquals("Employee deleted with id:1", result.getResponse().getContentAsString());
		
	}
	
	
	@Test
	public void searchByNameTest() throws Exception {
		
		Employee employee1 = new Employee();
		employee1.setId(4L);
		employee1.setFirstName("dinho");
		employee1.setLastName("ronald");
		employee1.setEmailId("dr@gmail.com");
		employee1.setDepartment("Java");
		employee1.setCity("Pune");
		
		List<Employee> list = new ArrayList<Employee>();
		list.add(employee1);
		
		String response = om.writeValueAsString(list);
		Mockito.when(service.findByFirstName("dinho")).thenReturn(list);
		MvcResult result = mockMvc
				.perform(get("/employees/findName/dinho").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		assertEquals(200, result.getResponse().getStatus());
	}
	
	
	
	
	
	
	
	
}
