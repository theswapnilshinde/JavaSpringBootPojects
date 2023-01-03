package com.rest.webservices.restfulwebservices.Controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rest.webservices.restfulwebservices.models.HelloWorldBean;

@RestController
public class Hello {

	@Autowired
	private MessageSource messageSource;

	public void Hello( MessageSource messageSourcee ) {
		this.messageSource= messageSourcee;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/hello")
	// internationalization send every launvage responce in applition hit url
	public String internationalizition() {

		Locale locale= LocaleContextHolder.getLocale() ;
		return	messageSource.getMessage("good.morning.message", null, "Default Message", locale);


	}
	@RequestMapping(method = RequestMethod.GET, path = "/hello1")
	public String hello() {
		return "Hello user";

	}


	@GetMapping(path="/hello-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello user");

	}


}
