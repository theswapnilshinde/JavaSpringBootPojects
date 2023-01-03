package com.ldapauth.config;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import javax.inject.Singleton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.ldapauth.common.dto.LoginDto;
import com.ldapauth.exception.TokenGenerationException;
import com.ldapauth.security.SecurityConstants;
import com.ldapauth.service.LoginService;
import com.ldapauth.utility.JwtTokenDateUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Singleton
public class TokenAuthentication {
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${client.accessTokenDuration}")
	private Integer clientAccesstoken;
	@Value("${client.refreshTokenDuration}")
	private Integer clientRefreshtoken;

	@Autowired 
	LoginService loginService;
	
	@Autowired
	Authentication authentication;
	
	public TokenAuthentication() {	
	}

	public Map<String, String> getToken(UserDetails loggedInUser, boolean isClientReq) throws IllegalArgumentException, Exception {
		Map<String, String> tokensMap = new HashMap<>();
		Calendar tokenExpiryTime = null;
		Calendar refreshtokenExpiryTime = null;
		if (isClientReq) {
			TimeZone timeZoneUTC = TimeZone.getTimeZone("UTC");
			tokenExpiryTime = Calendar.getInstance(timeZoneUTC);
		} else {
			tokenExpiryTime = Calendar.getInstance();
		}
		tokenExpiryTime.setTime(new Date());
		
		if (isClientReq) {
			tokenExpiryTime.add(Calendar.MINUTE, clientAccesstoken);
		}
		else
		{
			
			tokenExpiryTime.add(Calendar.MINUTE, clientAccesstoken);
		}		
		if (isClientReq) {
			TimeZone timeZoneUTC = TimeZone.getTimeZone("UTC");
			refreshtokenExpiryTime = Calendar.getInstance(timeZoneUTC);
		} else {
			refreshtokenExpiryTime = Calendar.getInstance();
		}
		refreshtokenExpiryTime.setTime(new Date());
		if (isClientReq) {
			refreshtokenExpiryTime.add(Calendar.HOUR,clientRefreshtoken);
		}
		else
		{
			refreshtokenExpiryTime.add(Calendar.HOUR,clientRefreshtoken);
		}
		try {
//			final UserDetails userDetails = loginService.loadUserByUsername(loggedInUser.getUserN);
			if(loggedInUser.getUsername() != null) {
//				final Algorithm algorithm = Algorithm.HMAC256(SecuirtyUtils.decrypt(secret));

				final Algorithm algorithm = Algorithm.HMAC256(secret);
				String token;
				String refreshToken;
				
				token = JWT.create().withIssuer("auth0")
						.withClaim("userName", loggedInUser.getUsername())	
						.withClaim("userRole", authentication.getUser(loggedInUser.getUsername()).getRoleName())	
						.withClaim("expiry", tokenExpiryTime.getTimeInMillis() + "").sign(algorithm);
				log.info("accessToken {} ", token);
				refreshToken = JWT.create().withIssuer("auth0")
						.withClaim("userName", loggedInUser.getUsername())	
						.withClaim("userRole", "ldap_admin")
						.withClaim("expiry", refreshtokenExpiryTime.getTimeInMillis() + "").withSubject("refresh")
						.sign(algorithm);			
				tokensMap.put("accessToken", token);
				tokensMap.put("refreshToken", refreshToken);
				tokensMap.put("type", SecurityConstants.TOKEN_PREFIX);
				tokensMap.put("userRole", authentication.getUser(loggedInUser.getUsername()).getRoleName());
				tokensMap.put("expiry", JwtTokenDateUtil.jwtTokenDateToLMFormat(tokenExpiryTime.getTime() + ""));
				tokensMap.put("refreshExpiry", JwtTokenDateUtil.jwtTokenDateToLMFormat(refreshtokenExpiryTime.getTime() + ""));
			}
			
			return tokensMap;
		}catch (final JWTCreationException exception) {
			throw new TokenGenerationException("Token could not be generated", exception);
		}
		
	}
	
}

