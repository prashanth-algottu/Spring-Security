package com.tec.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception 
	{
		auth.inMemoryAuthentication().withUser("chinnu").password("{noop}chinnu").authorities("admin");
		auth.inMemoryAuthentication().withUser("sujji").password("{noop}sujji").authorities("emp");
		auth.inMemoryAuthentication().withUser("vinnu").password("{noop}vinnu").authorities("std");
		
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception 
	{
		http.authorizeHttpRequests()
		//URL Access Type
		.antMatchers("/home").permitAll()
		.antMatchers("/welcome").authenticated()
		.antMatchers("/admin").hasAuthority("admin")
		.antMatchers("/emp").hasAuthority("emp")
		.antMatchers("/StdPage").hasAuthority("std")
		
		
		// Login Form Details
		.and()
		.formLogin()
		.defaultSuccessUrl("/welcome", true)
	
			
		//LogOut Details
		.and()
		.logout()
		
		
		// Exceptions
		.and()
		.exceptionHandling()
		.accessDeniedPage("/denied")
		;
	}

	

}
