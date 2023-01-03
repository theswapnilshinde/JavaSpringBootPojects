package com.ldapauth.common.dto;

//import org.hibernate.validator.constraints.Email;
//import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginDto {

	//@Email
	String userName;
	
//	@NotEmpty(message = "Please enter valid password.")
	String pwd;

	String accessCode;
	
	boolean resendOtp;

	String accesskey;

	String userid;
	
	String email;
	String firstName;
	String lastName;	
	private Integer statusCode;
	private String message;
	private Object data;
}

