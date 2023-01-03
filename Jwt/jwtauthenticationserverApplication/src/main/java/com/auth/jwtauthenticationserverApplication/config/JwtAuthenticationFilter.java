package com.auth.jwtauthenticationserverApplication.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth.jwtauthenticationserverApplication.helper.JwtUtil;
import com.auth.jwtauthenticationserverApplication.services.CustomUserDetailsService;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private CustomUserDetailsService CustomUserDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String requestTokenHeader = request.getHeader("Authorization");
		String username = null;
		String jwtToken = null;
		
	//	if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer")) {
			if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer ")) {
			
			jwtToken = requestTokenHeader.substring(7);
			
			try {
				
				username = this.jwtUtil.extractUsername(jwtToken);
			} catch (Exception e) {
				e.getStackTrace();
			}
			
			//security
			UserDetails userDetails = this.CustomUserDetailsService.loadUserByUsername(username);
	//		CustomUserDetailsService userDetails= this.CustomUserDetailsService.loadUserByUsername(username);
			if (username != null && SecurityContextHolder.getContext().getAuthentication()!=null) {

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails.getAuthorities(), userDetails, null);
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			} else {

				System.out.println("Token not validated");
			}
		}
		filterChain.doFilter(request, response);

	}
	

}
