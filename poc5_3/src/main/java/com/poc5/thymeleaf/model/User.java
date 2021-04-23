package com.poc5.thymeleaf.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "users")
@Getter 
@Setter
public class User {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@Column(name = "last_name", nullable = false)
	private String lastName;
	
	@Column(name = "full_name")
	private String fullName;
	
	@Column(name = "contact")
	private String contact;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "country")
	private String country;
	
}

