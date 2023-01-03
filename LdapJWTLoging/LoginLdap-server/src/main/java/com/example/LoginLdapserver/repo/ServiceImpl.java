








package com.example.LoginLdapserver.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Component;

import com.example.LoginLdapserver.Person;
import com.example.LoginLdapserver.PersonVO;

@Component
public class ServiceImpl implements Service {

	@Autowired
	private Repo repo;
	
	
	@Override
	public String create(PersonVO personVO) {
		Person person= new Person();
		person.setDn(LdapNameBuilder.newInstance(personVO.getDn()).build());
		person.setFullname(personVO.getFullname());
		person.setLastname(personVO.getLastname());
		person.setDescription(personVO.getDescription());
		person.setGivename(personVO.getGivename());
		person.setMail(personVO.getMail());
		person.setUid(personVO.getUid());
		
		return repo.create(person);
	}

	
	@Override
	public List<Person> findAll() {
		Iterable<Person> itr= repo.findAll();//.findAll();
		List<Person> list= new ArrayList<>();
		
		for(Person person2:itr)
		{
			list.add(person2);
		}
		
		return list;
	}

	@Override
	public List<Person> findByLastName(String lastname) {
		
		Iterable<Person> itr= repo.findAll(LdapQueryBuilder.query().where("sn").is(lastname));
		List<Person> list= new ArrayList<>();
		
		for(Person person2:itr)
		{
			list.add(person2);
		}
		System.out.println(list);
		return list;
	}

	@Override
	public List findByUid(String uid) {

		Iterable<Person> itr= repo.findAll(LdapQueryBuilder.query().where("uid").is(uid));
		List<Person> list= new ArrayList<>();
		
		for(Person person2:itr)
		{
			list.add(person2);
		}
		System.out.println(list);
		return list;
		
	}
	
	@Override
	public String update(PersonVO p) {
		
		return null;
	}

	@Override
	public String delete(PersonVO p) {
		// TODO Auto-generated method stub
		return null;
	}


}
