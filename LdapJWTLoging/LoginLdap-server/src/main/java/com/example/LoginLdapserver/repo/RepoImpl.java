package com.example.LoginLdapserver.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;

import com.example.LoginLdapserver.Person;

public class RepoImpl implements RepoAdditional {

	
	@Autowired
	private LdapTemplate ldapTemplate;
	
	@Override
	public String create(Person p) {
		
		ldapTemplate.create(p);
		
		System.out.println("Sucsees Class Created");
		return "success";
		
		
	}

}
