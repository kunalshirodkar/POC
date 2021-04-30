package com.poc6.test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc6.controller.UserController;
import com.poc6.model.User;
import com.poc6.repository.UserRepository;
import com.poc6.service.UserService;

@WebMvcTest(UserController.class)
public class UserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserRepository repo;
	
	@MockBean
	private UserService service;
	
	ObjectMapper om = new ObjectMapper();
	
	public static List<User> setUp() {
		User user1 = new User();
		user1.setId(1L);
		user1.setFirstName("kunal");
		user1.setLastName("shirodkar");
		user1.setContact("9876543210");
		user1.setEmail("ks@gmail.com");
		user1.setCity("mumbai");
		user1.setCountry("India");
		user1.setPinCode("400068");
		
		User user2 = new User();
		user2.setId(2L);
		user2.setFirstName("priti");
		user2.setLastName("bhutelo");
		user1.setContact("9876543201");
		user2.setEmail("pb@gmail.com");
		user2.setPinCode("400069");
		user2.setCity("kolhapur");
		user2.setCountry("India");

	
		List<User> listUser = new ArrayList<User>();
		listUser.add(user1);
		listUser.add(user2);
		
		return listUser;
	}
	
	@Test
	public void addUserTest() throws Exception {               
		Mockito.when(service.addUser(UserControllerTest.setUp().get(0))).thenReturn(1L);   
		String payload = om.writeValueAsString(UserControllerTest.setUp().get(0));        
		MvcResult result = mockMvc
				.perform(post("/users").content(payload).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isCreated()).andReturn();
		int status = result.getResponse().getStatus();
		assertEquals(201, status);
		
	}
	
	@Test
	public void getAllUsersTest() throws Exception {         
		List<User> lst = UserControllerTest.setUp();
		String response = om.writeValueAsString(lst);
		Mockito.when(service.getUsers()).thenReturn(lst);
		MvcResult result = mockMvc.perform(get("/users")
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		String userRes = result.getResponse().getContentAsString();
		assertEquals(response, userRes);
				
	}
	
	@Test
	public void updateUserTest() throws Exception {
		Mockito.when(service.updateUser(UserControllerTest.setUp().get(1).getId(),UserControllerTest.setUp().get(1))).thenReturn(UserControllerTest.setUp().get(1));
		String payload = om.writeValueAsString(UserControllerTest.setUp().get(1));
		MvcResult result = mockMvc
				.perform(put("/users/update/1").content(payload).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		int status = result.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	@Test
	public void getUserbyIdTest() throws Exception {
		String response=om.writeValueAsString(UserControllerTest.setUp().get(0));
		Mockito.when(service.getUser(1L)).thenReturn(java.util.Optional.of(UserControllerTest.setUp().get(0)));
		MvcResult result = mockMvc.perform(get("/users/1").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		int response1 = result.getResponse().getStatus();
		assertEquals(200, response1);
		
	}
	
	@Test
	public void deleteUserTest() throws Exception {
		
		MvcResult result = mockMvc.perform(delete("/users/delete/1").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		int response = result.getResponse().getStatus();
		assertEquals(200, response);
		assertEquals("User deleted with id:1", result.getResponse().getContentAsString());
		
	}
	
	@Test
	public void searchByNameTest() throws Exception {
		User user1 = new User();
		user1.setId(4L);
		user1.setFirstName("dinho");
		user1.setLastName("ronald");
		user1.setContact("9786543211");
		user1.setEmail("dr@gmail.com");
		user1.setPinCode("400011");
		user1.setCity("Pune");
		user1.setCountry("India");
		List<User> list = new ArrayList<User>();
		list.add(user1);
		
		String response = om.writeValueAsString(list);
		Mockito.when(service.findByFirstName("dinho")).thenReturn(list);
		MvcResult result = mockMvc
				.perform(get("/users/findName/dinho").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		assertEquals(200, result.getResponse().getStatus());
	}
	
	@Test
	public void searchByPinCodeTest() throws Exception {
		User user1 = new User();
		user1.setId(4L);
		user1.setFirstName("dinho");
		user1.setLastName("ronald");
		user1.setContact("9786543211");
		user1.setEmail("dr@gmail.com");
		user1.setPinCode("400011");
		user1.setCity("Pune");
		user1.setCountry("India");
		
		User user2 = new User();
		user2.setId(5L);
		user2.setFirstName("dinho1");
		user2.setLastName("ronald1");
		user2.setContact("9786543200");
		user2.setEmail("dr1@gmail.com");
		user2.setPinCode("400012");
		user2.setCity("Pune");
		user2.setCountry("India");
		List<User> list = new ArrayList<User>();
		list.add(user1);
		list.add(user2);
		
		String response = om.writeValueAsString(list);
		Mockito.when(service.findByPinCode("400011")).thenReturn(list);
		MvcResult result = mockMvc
				.perform(get("/users/findPinCode/400011").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		assertEquals(200, result.getResponse().getStatus());
	}
	

}
