package com.webupps.custom.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	 @Autowired
	  PasswordEncoder passwordEncoder;
	 
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//super.configure(auth);
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
    	String encodedPassword = passwordEncoder.encode("password");
    	
		auth.inMemoryAuthentication()
			.withUser("blah")
			.password(encodedPassword)
			.roles("USER");
	}
	
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
	   //return NoOpPasswordEncoder.getInstance();
		return new BCryptPasswordEncoder();
	}
	
}

