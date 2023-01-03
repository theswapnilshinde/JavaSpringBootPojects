package com.vzsme.controllerService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.*;

import javax.annotation.PostConstruct;
import java.util.TimeZone;


@SpringBootApplication
//@EnableSwagger2
public class Application {

	@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC")); // Sets the entire application timestamps to be UTC
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/**")
						.allowedOrigins("*").allowedMethods("GET", "POST", "OPTIONS", "PUT")
						.allowedHeaders("Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
								"Access-Control-Request-Headers")
						.exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
						.allowCredentials(false).maxAge(3600);
			}
		};
	}
}
