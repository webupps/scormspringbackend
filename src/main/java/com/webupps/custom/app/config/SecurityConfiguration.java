package com.webupps.custom.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.webupps.custom.app.repository.UsersRepository;
import com.webupps.custom.app.service.DefaultUserDetailsService;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	 
	 /*
	 @Autowired
	 private UsersRepository usersRepository;
	 
	 	@Override
	    @Bean
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	 		System.out.println("test 1");
	        return super.authenticationManagerBean();
	    }
	
	    @Autowired
	    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
	       // auth.userDetailsService(new DefaultUserDetailsService(userRepository));
	    	System.out.println("test 2");
	    	auth.userDetailsService(new DefaultUserDetailsService(usersRepository));
	    }*/
	 
	  @Autowired
	  private UsersRepository usersRepository;
	 
	  @Autowired
	  PasswordEncoder passwordEncoder;
	  
	    @Override
	    @Bean
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	 		System.out.println("test 1");
	        return super.authenticationManagerBean();
	    }
	
	    @Autowired
	    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
	       // auth.userDetailsService(new DefaultUserDetailsService(userRepository));
	    	System.out.println("test 2");
	    	auth.userDetailsService(new DefaultUserDetailsService(usersRepository));
	    }
	  
	 protected void configure(HttpSecurity http) throws Exception {
		 
		 http   .logout()
                 .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                 .and()
			    .authorizeRequests()
			    .antMatchers("/v1/admin/**").hasRole("ADMIN")
				.antMatchers("/v1/greeting/hello").hasRole("USER")
				.antMatchers(HttpMethod.POST,"/v1/users/registration").permitAll()
				.antMatchers("/logout").permitAll()
				.antMatchers("/").permitAll()
		        .anyRequest().authenticated()
		        .and().csrf().disable()
		    .formLogin()
		        .and()
		    .httpBasic().disable();
	 }
	 
	
	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(new DefaultUserDetailsService(usersRepository));
		
	}
	
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
	   //return NoOpPasswordEncoder.getInstance();
		return new BCryptPasswordEncoder();
	}
	
	
	
	
	
}

