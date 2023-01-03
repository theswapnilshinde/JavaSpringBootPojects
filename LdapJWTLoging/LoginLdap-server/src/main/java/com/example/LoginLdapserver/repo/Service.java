package com.example.LoginLdapserver.repo;

import java.util.List;

import com.example.LoginLdapserver.Person;
import com.example.LoginLdapserver.PersonVO;

public interface Service {
	
	String create (PersonVO p);
	String update (PersonVO p);
	String delete (PersonVO p);
	List<Person>  findAll();
	List <Person> findByLastName(String lastname);
//    List findById(String uid);
	List findByUid(String uid);
}
