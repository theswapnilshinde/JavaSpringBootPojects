package com.example.LoginLdapserver.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.LoginLdapserver.Person;
import com.example.LoginLdapserver.PersonVO;
import com.example.LoginLdapserver.repo.Service;

@RestController
@RequestMapping("/ldap")
public class HomeController {
	
	@Autowired
	private Service service;
	
	@RequestMapping(value ="/add",method=RequestMethod.POST)
	public String login (@RequestBody PersonVO person)
	{
		
		return service.create(person);
		
	}
	
	@RequestMapping(value ="/update",method=RequestMethod.POST)
	public String createUpdate (@RequestBody PersonVO person)
	{
		
		return service.create(person);
		
	}
	@RequestMapping(value ="/list",method=RequestMethod.GET)
	public List<?> list ()
	{
		
		return service.findAll();
		
	}
	
	@RequestMapping(value ="/name",method=RequestMethod.GET)
	public List<Person> findlastname (@RequestBody String string)
	{
		
		return service.findByLastName(string)  ;
		
	}
	
	@RequestMapping(value ="/uid",method=RequestMethod.GET)
	public List<Person> findUid (@RequestBody String string)
	{
		
		return service.findByUid(string)  ;
		
	}
	
	

}
