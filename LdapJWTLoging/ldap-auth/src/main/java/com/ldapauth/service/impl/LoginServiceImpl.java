package com.ldapauth.service.impl;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ldapauth.common.dto.UserResData;
import com.ldapauth.config.Authentication;
import com.ldapauth.service.LoginService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	Authentication authentication;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("inside loadUserByUsername");
		try {
			UserResData userResData = authentication.getUser(username);

			if (Optional.ofNullable(userResData).isPresent()) {
				
				log.info("RoleName {} ",userResData.getRoleName());
				PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
				return new User(username,passwordEncoder.encode(userResData.getPassword()),new ArrayList<>());
			}else {
				log.info("User not found name {} ",username);
				throw new UsernameNotFoundException("User not found name is "+username);	
			}
		}catch (Exception e) {
			log.info("User not found name {} ",username);
			throw new UsernameNotFoundException("User not found name is "+username);
		}
		
	}

}
