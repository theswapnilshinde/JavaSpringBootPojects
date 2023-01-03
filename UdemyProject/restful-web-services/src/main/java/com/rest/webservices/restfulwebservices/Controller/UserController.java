package com.rest.webservices.restfulwebservices.Controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.BasicLinkBuilder;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rest.webservices.restfulwebservices.exceptions.UserNotFoundException;
import com.rest.webservices.restfulwebservices.user.User;
import com.rest.webservices.restfulwebservices.user.UserDao;

import jakarta.validation.Valid;

@RestController
public class UserController {

	@Autowired
	public UserDao service;


	@GetMapping("/users")
	public List<User> retriveAllUsers(){
		return service.findAll();

	}

	@GetMapping("/users/{id}")
	public User retriveUsers(@PathVariable int id){
		User user= service.findOne(id);


		if(user==null)
	     throw new UserNotFoundException("id"+id);
		return user;


	}

	@PostMapping("/users")
	public ResponseEntity<User> saveUser(@Valid  User user) {
		// TODO Auto-generated method stub
		User savedusers= service.saveUser(user);
		URI location= ServletUriComponentsBuilder.fromCurrentRequestUri()
				.path("/id")
				.buildAndExpand(savedusers.getId())
				.toUri();
		return ResponseEntity.created(location).build();

	}

// http://localhost:9090/users
	// EntityModel 
	// WebMvcLinkBulder
	
	@GetMapping("/user/hateoas/{id}")
	public EntityModel<User> userWhitLint( @PathVariable int id)
	{
		
		User user = service.findOne(id);
		
		if(user==null)throw new  UserNotFoundException("id:"+id);
		
		EntityModel<User> entityModel= EntityModel.of(user);
		
		//create link add in class object 
		//WebMvcLinkBuilder link = LinkTo(methodOn(this.getClass()).retriveAllUser());
		//BasicLinkBuilder linkTo=linkTo(methodOn(this.getClass().retriveAllUsers(()); 
		// entityModel.add(link,withRel("users"));
		 
		 return entityModel;
		
	}

}
