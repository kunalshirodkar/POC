package com.poc5.thymeleaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poc5.thymeleaf.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}

