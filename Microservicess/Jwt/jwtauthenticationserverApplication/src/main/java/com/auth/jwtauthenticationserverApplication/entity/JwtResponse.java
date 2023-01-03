package com.auth.jwtauthenticationserverApplication.entity;

public class JwtResponse {
	
	private String token;
	
	public JwtResponse() {
		// TODO Auto-generated constructor stub
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public JwtResponse(String token) {
		super();
		this.token = token;
	}

}
