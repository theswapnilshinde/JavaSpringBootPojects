package com.rest.webservices.restfulwebservices.user;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
@Entity(name="user_details")
public class User {

   @Id
	private int id;
	@Size(min=4)

	// costumizing json properties  implimenting 
	//static filtering change in reponce
	@JsonProperty("user_name")
	private String name;
	@Past
	
	@JsonProperty("birth_date")
	private LocalDate birthDate;

	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	public User(int id, String name, LocalDate brithDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = brithDate;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	


}
