package com.poc6.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc6.exception.ResourceNotFoundException;
import com.poc6.model.User;
import com.poc6.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		userRepository.findAll().forEach(users::add);
		return users;
	}
	
	public Optional<User> getUser(Long id) {
		return userRepository.findById(id);
	}
	
	public Long addUser(User user) {
		 userRepository.save(user);
		 return user.getId();
	}
	
	public String deleteUser(Long id) {
		userRepository.deleteById(id);
		return "User deleted with id:"+id;
	}
	
	public List<User> findByFirstName(String firstName) {
		return userRepository.findByFirstName(firstName);
	}
	
	public List<User> findByPinCode(String pincode) {
		return userRepository.findByPinCode(pincode);
	}
	
	public User updateUser(Long id,User user){
		return userRepository.findById(id).map(u->{
			u.setContact(user.getContact());
			u.setEmail(user.getEmail());
			u.setPinCode(user.getPinCode());
			u.setCity(user.getCity());
			u.setCountry(user.getCountry());
			return userRepository.save(u);
		}).orElseThrow(()->new ResourceNotFoundException("Id"+id+"not found"));
	}

}
