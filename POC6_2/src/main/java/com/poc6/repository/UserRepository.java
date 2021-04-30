package com.poc6.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.poc6.model.User;

public interface UserRepository extends CrudRepository<User, Long>{
	List<User> findByFirstName(String firstName);
	List<User> findByPinCode(String pinCode);
}
