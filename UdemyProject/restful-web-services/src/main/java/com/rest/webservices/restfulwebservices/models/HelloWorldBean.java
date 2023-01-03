package com.rest.webservices.restfulwebservices.models;

public class HelloWorldBean {

	public HelloWorldBean(String message) {
		super();
		this.message = message;
	}

	private String message;

	public HelloWorldBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "HelloWorldBean [message=" + message + "]";
	}



}
