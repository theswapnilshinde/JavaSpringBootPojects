package com.example.LoginLdapserver;

import javax.naming.Name;

import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;
@Entry(objectClasses = {"inetOrgPerson","organizationPerson","person","top"})
public class PersonVO {
	
    @Id
	private String dn;
   
   @Attribute(name="cn")
	private String fullname;
   
   @Attribute(name="sn")
	private String lastname;
   
   @Attribute(name="givename")
	private String givename;
   
   @Attribute(name="description")
	private String description;
   
   @Attribute(name="mail")
	private String mail;
   
   @Attribute(name="uid")
	private String uid;
   
	public PersonVO() {
		super();
		
	}

	

	public String getDn() {
		return dn;
	}



	public void setDn(String dn) {
		this.dn = dn;
	}



	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

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

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}



	public PersonVO(String dn, String fullname, String lastname, String givename, String description, String mail,
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
