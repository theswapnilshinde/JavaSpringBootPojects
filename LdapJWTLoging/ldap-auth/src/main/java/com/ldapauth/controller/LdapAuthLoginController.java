package com.ldapauth.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.web.client.RestTemplate;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.ldapauth.common.dto.LoginDto;
import com.ldapauth.common.dto.ResponseDto;
import com.ldapauth.config.JwtTokenUtil;
import com.ldapauth.config.TokenAuthentication;
import com.ldapauth.security.SecurityConstants;
import com.ldapauth.service.LoginService;
import com.ldapauth.shared.Constants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("${app.root}")
public class LdapAuthLoginController {

	@Autowired
	LoginService loginService;
	
	@Autowired
    private AuthenticationManager authenticationManager;
    

//    @Autowired
//    private JwtUserDetailsService userDetailsService;
    
//	@Autowired 
//	private JwtTokenUtil jwtTokenUtil;
	
	
	@Autowired
	TokenAuthentication tokenAuthentication;
	
	@PostMapping(Constants.LDAP_LOGIN)
	public ResponseEntity<ResponseDto> getLDAPLoginData(@Valid @RequestBody LoginDto loggedInUser) {
		final ResponseDto responseData = new ResponseDto();
//		String[] splitEmail = null;
		try {
			if (loggedInUser.getUserName() == null && loggedInUser.getPwd() == null
					&& StringUtils.isEmpty(loggedInUser.getUserName()) && StringUtils.isEmpty(loggedInUser.getPwd())) {
				responseData.setStatusCode(HttpStatus.BAD_REQUEST.value());
				responseData.setMessage("Please provide user name (email) and Password");
				log.info("Please provide user name (email) and Password");
				return new ResponseEntity<>(responseData, HttpStatus.EXPECTATION_FAILED);
			} else {
				boolean isValiduser = false;
				isValiduser=authenticate(loggedInUser.getUserName(), loggedInUser.getPwd());
//				splitEmail = loggedInUser.getUserName().split("@");
//				log.info(splitEmail[0] + " fetching from LDAP " + splitEmail[1]);
		
				log.info("isValiduser: " + isValiduser);
				if (isValiduser) {
					final UserDetails userDetails = loginService
			                .loadUserByUsername(loggedInUser.getUserName());
					
					final Map<String, String> tokenMap = tokenAuthentication.getToken(userDetails, false);					
					responseData.setToken(tokenMap.get("accessToken"));
					responseData.setRefreshToken(tokenMap.get("refreshToken"));
					responseData.setUserId(loggedInUser.getUserName());
					responseData.setType(tokenMap.get("type"));
					responseData.setUserRole(tokenMap.get("userRole"));
					responseData.setStatusCode(HttpStatus.OK.value());
					responseData.setMessage(Constants.USER_LOGIN);
					return new ResponseEntity<ResponseDto>(responseData, HttpStatus.OK);
				} else {
					responseData.setStatusCode(HttpStatus.UNAUTHORIZED.value());
					responseData.setMessage("Invalid Username/Password");
					log.info("Invalid Username/Password");
					return new ResponseEntity<ResponseDto>(responseData, HttpStatus.UNAUTHORIZED);
				}
			}
		} catch (Exception e) {
			responseData.setStatusCode(HttpStatus.BAD_REQUEST.value());
			responseData.setMessage("Exception occurred while logging : " + e.getMessage());
			return new ResponseEntity<ResponseDto>(responseData, HttpStatus.EXPECTATION_FAILED);
		}
	}
	 private boolean authenticate(String username, String password) throws Exception {
	        try {
	            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
	            return true;
	        } catch (DisabledException e) {
	            throw new Exception("USER_DISABLED", e);
	        } catch (BadCredentialsException e) {
	            throw new Exception("INVALID_CREDENTIALS", e);
	        }
	    }

	public ResponseEntity<LoginDto> getLDAPData(LoginDto loggedInUser) {
//		RestTemplate restTemplate = new RestTemplate();
		Map<String, String> params = new HashMap<String, String>();
		params.put("userName", loggedInUser.getUserName());
		String hashPwd = SecurityConstants.hashPassword(loggedInUser.getPwd());
		params.put("pwd", hashPwd);
		params.put("accessCode", loggedInUser.getAccessCode());
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResponseEntity<LoginDto> result = null;
		HttpEntity<Map<String, String>> requestEntity = new HttpEntity<Map<String, String>>(params, headers);
//		ResponseEntity<LoginDto> result = restTemplate.exchange("ldapURL", HttpMethod.PUT, requestEntity,
//				LoginDto.class);
		
		
		return result;
	}
}
