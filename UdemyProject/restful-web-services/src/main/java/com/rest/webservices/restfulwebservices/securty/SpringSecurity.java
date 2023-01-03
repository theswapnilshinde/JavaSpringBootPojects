package com.rest.webservices.restfulwebservices.securty;

import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurity {
	
	@Bean
	public SecurityFilterChain filterChin(HttpSecurity http) throws Exception
	{
		// all request should be authenticated
		http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated() );
		
		// if a request is not authonticate a web page is shown
		http.httpBasic(withDefaults()  );
		
		// CSRF -> POST ,PUT ;
		//http.csrf().disable();
		
		return http.build();
		
	}

}
