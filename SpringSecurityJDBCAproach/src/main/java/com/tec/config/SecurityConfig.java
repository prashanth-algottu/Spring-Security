package com.tec.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	
	@Autowired
	private DataSource dataSource;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder; 
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
		// creates database connection
		.dataSource(dataSource)
		// fetch un,pwd, enabled using username input entered in login page
		.usersByUsernameQuery("select  un,pwd,uenables from passwords where un=?")
		// fetch un,urole using username input entered in login page
		.authoritiesByUsernameQuery("select un,urole from passwords where un=?")
		// Provide password encoder obj reference
		.passwordEncoder(bCryptPasswordEncoder)
		
		;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception 
	{
		http.authorizeRequests()
		.antMatchers("/home").permitAll()
		.antMatchers("/welcome").authenticated()
		.antMatchers("/admin").hasAnyAuthority("admin")
		.antMatchers("/emp").hasAnyAuthority("emp")
		.antMatchers("/std").hasAnyAuthority("std")
		.anyRequest().authenticated()
		
		
		.and()
		.formLogin()
		.defaultSuccessUrl("/welcome", true)
		
		
		.and()
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		
		
		.and()
		.exceptionHandling()
		.accessDeniedPage("/denied")
		
		;
		
		
		
		
	}

}
