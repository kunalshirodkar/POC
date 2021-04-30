package com.poc6.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poc6.model.User;
import com.poc6.repository.UserRepository;
import com.poc6.service.UserService;


@RestController
public class UserController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    UserService userService;
	
	@Autowired
	UserRepository repository;

	@GetMapping("/users")    
	public List<User> getUsers() {
		logger.info(".....Getting all users.....");
		return userService.getUsers();
	}
	
	@RequestMapping("/users/{id}")
	public Optional<User> getUser(@PathVariable Long id) {
		logger.info(".....Getting users by their id.....");
		return userService.getUser(id);
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> addUser(@RequestBody User user) {
		logger.info(".....Adding User.....");
		userService.addUser(user);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/users/update/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id,@RequestBody User user) {
		logger.info(".....Updating user by id.....");
		userService.updateUser(id,user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/users/delete/{id}")
	public String deleteUser(@PathVariable Long id) {
		logger.info(".....Deleting user by id.....");
		userService.deleteUser(id);
		return "User deleted with id:"+id;
	}
	
	@GetMapping(value = "/users/findName/{firstName}")
	public List<User> findByFirstName(@PathVariable String firstName) {
		logger.info(".....Searching user by first name.....");
		return userService.findByFirstName(firstName);
	}
	
	@GetMapping(value = "/users/findPinCode/{pincode}")
	public List<User> findByPinCode(@PathVariable String pincode) {
		logger.info(".....Searching user by pincode.....");
		return userService.findByPinCode(pincode);
	}
	
	

}
