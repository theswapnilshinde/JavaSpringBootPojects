package com.example.LoginLdapserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.ldap.repository.config.EnableLdapRepositories;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

import com.example.LoginLdapserver.repo.RepoAdditional;
import com.example.LoginLdapserver.repo.RepoImpl;

@Configuration
@EnableLdapRepositories
public class Config {
	
	@Bean
	public LdapContextSource ldapContextSource() {		
		LdapContextSource lcs= new LdapContextSource();
		//lcs.setUrl("ldap://localhost:10389");
		//  ldap://localhost:10380/ou=users,ou=system
				
		lcs.setUrl("ldap://localhost:10389");
		lcs.setBase("dc=swapnil,dc=com");
		
//	lcs.setBase("ou=users,ou=system");
		
		//ou=users,ou=system
	//	dc=swapnil,dc=com
		//lcs.setBase("dc=swapnil,dc=com");	
		return lcs;
	}
	
	@Bean
	public LdapTemplate ldapTemplate() {
		
		return new LdapTemplate(ldapContextSource());
		
	}
	
	@Bean
	public RepoAdditional repoAdditional(){			
		return new RepoImpl();
	}

}
