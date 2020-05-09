package com.webupps.custom.app.config;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

//import com.webupps.custom.app.repository.UsersRepository;
//import com.webupps.custom.app.service.DefaultUserDetailsService;

//@Configuration
//@EnableWebSecurity
public class SecurityConfig {
	/*
    @Autowired
	private UsersRepository usersRepository; */
    
    protected void configure(HttpSecurity http) throws Exception {     
        http
        .csrf().disable()
        .authorizeRequests()
            .antMatchers("/v1/users/**").permitAll();
       // .and()
        //.httpBasic().realmName("My-Realm");
    	
    }
    
  //.antMatchers("/v1/admin/**").hasRole("ADMIN")
    //.anyRequest().authenticated()
	 /*
     @Autowired
     public void configureGlobal(AuthenticationManagerBuilder auth)
             throws Exception
     {
         auth.userDetailsService(new DefaultUserDetailsService(usersRepository));        
     }*/
     
}
