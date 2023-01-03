package com.example.LoginLdapserver;

import javax.naming.Name;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

@Entry(objectClasses = { "inetOrgPerson","organizationalPerson","person","top" })

public class Person {
	
	   @Id    
		private Name dn;
	   
	   @Attribute(name="cn")
		private String fullname;
	   
	   @Attribute(name="sn")
		private String lastname;
	   
	   @Attribute(name="givenname")
		private String givename;
	   
	   @Attribute(name="description")
		private String description;
	   
	   @Attribute(name="mail")
		private String mail;
	   
	   @Attribute(name="uid")
		private String uid;
	   
	   
	   public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getGivename() {
		return givename;
	}
	public void setGivename(String givename) {
		this.givename = givename;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;

	}
	   
	public Name getDn() {
		return dn;
	}
	public void setDn(Name dn) {
		this.dn = dn;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Person(Name dn, String fullname, String uid) {
		super();
		this.dn = dn;
		this.fullname = fullname;
		this.uid = uid;
	}
	@Override
	public String toString() {
		return "Person [dn=" + dn + ", fullname=" + fullname + ", lastname=" + lastname + ", givename=" + givename
				+ ", description=" + description + ", mail=" + mail + ", uid=" + uid + "]";
	}
	public Person(Name dn, String fullname, String lastname, String givename, String description, String mail,
			String uid) {
		super();
		this.dn = dn;
		this.fullname = fullname;
		this.lastname = lastname;
		this.givename = givename;
		this.description = description;
		this.mail = mail;
		this.uid = uid;
	}
	
	
	
	

}
