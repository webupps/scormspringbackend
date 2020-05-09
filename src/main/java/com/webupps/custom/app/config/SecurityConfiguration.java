package com.webupps.custom.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.webupps.custom.app.repository.UsersRepository;
import com.webupps.custom.app.service.DefaultUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	 @Autowired
	  PasswordEncoder passwordEncoder;
	 
	 @Autowired
	 private UsersRepository usersRepository;
	 
	 protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests()
				.antMatchers("/v1/admin/**").hasRole("ADMIN")
				.antMatchers("/v1/user/**").hasRole("USER")
				.antMatchers("/").permitAll()
				.and().formLogin();
	}
	 
	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		//super.configure(auth);
		/* PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
    	String encodedPassword = passwordEncoder.encode("password");
    	
		auth.inMemoryAuthentication()
			.withUser("blah")
			.password(encodedPassword)
			.roles("USER"); */
		
		//auth.userDetailsService(new DefaultUserDetailsService(usersRepository)); 
    	auth.userDetailsService(new DefaultUserDetailsService(usersRepository));
		
	}
	
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
	   //return NoOpPasswordEncoder.getInstance();
		return new BCryptPasswordEncoder();
	}
	
	
	
	
	
}

