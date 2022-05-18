package com.tec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class Configure {
	
	@Bean
	public BCryptPasswordEncoder encode()
	{
		return new BCryptPasswordEncoder();
	}

}
