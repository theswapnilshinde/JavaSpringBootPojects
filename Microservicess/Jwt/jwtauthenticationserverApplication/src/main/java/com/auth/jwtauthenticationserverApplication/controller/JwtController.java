package com.auth.jwtauthenticationserverApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.auth.jwtauthenticationserverApplication.entity.JwtRequest;
import com.auth.jwtauthenticationserverApplication.entity.JwtResponse;
import com.auth.jwtauthenticationserverApplication.helper.JwtUtil;
import com.auth.jwtauthenticationserverApplication.services.CustomUserDetailsService;


@RestController
public class JwtController {
	@Autowired
	private AuthenticationManager authenticationManager;
	
    @Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@RequestMapping(value = "/token",method = RequestMethod.POST)
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		
		System.out.println(jwtRequest);
		try {
			//System.out.println("entred in controller"+ jwtRequest);
			 this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
		} catch (UsernameNotFoundException e) {
			e.getStackTrace();
			throw new Exception("Bad credentials");
		}
		UserDetails userDetailsl = this.customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());

		String token = this.jwtUtil.generateToken(userDetailsl);
		System.out.println("JWT"+token);
		
		return ResponseEntity.ok(new JwtResponse(token));
	}

}
