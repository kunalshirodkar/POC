package com.poc5.thymeleaf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.poc5.thymeleaf.model.User;
import com.poc5.thymeleaf.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/")										// display list of users
	public String viewHomePage(Model model) {
		return findPaginated(1, model);		
	}
	
	@GetMapping("/showNewUserForm")
	public String showNewUserForm(Model model) {
		
		User user = new User();								// create model attribute to bind form data
		model.addAttribute("user", user);
		return "new_user";
	}
	
	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute("user") User user) {
		
		userService.saveUser(user);							// save user to database
		return "redirect:/";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id")long id,Model model) {
		
		
		User user = userService.getUserById(id);			// get user from the service
				
		model.addAttribute("user", user);					// set user as a model attribute to pre-populate the form
		return "update_user";
	}
	
	@GetMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable (value = "id")long id) {
				
		this.userService.deleteUserById(id);				// call delete user method 
		return "redirect:/";
	}
	
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo")int pageNo,Model model)  {
		int pageSize = 5;
		
		Page<User> page = userService.findPaginated(pageNo, pageSize);
		List<User> listUsers = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());		
		model.addAttribute("listUsers", listUsers);
		
		return "index";
	}
}
