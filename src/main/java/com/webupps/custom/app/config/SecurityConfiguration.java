package com.webupps.custom.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
private DataSource dataSource;
    
    private PasswordEncoder passwordEncoder;
    private UserDetailsService userDetailsService;
    

    public SecurityConfiguration(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        if (passwordEncoder == null) {
        	Map<String, PasswordEncoder> encoders = new HashMap<>();
        	encoders.put("bcrypt", new BCryptPasswordEncoder());
        	DelegatingPasswordEncoder delegatingPasswordEncoder = 
        			 new DelegatingPasswordEncoder("bcrypt",encoders);        	
        	delegatingPasswordEncoder.setDefaultPasswordEncoderForMatches(new BCryptPasswordEncoder(10));
        	
            passwordEncoder = delegatingPasswordEncoder;
        }
        return passwordEncoder;
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        if (userDetailsService == null) {
            userDetailsService = new JdbcDaoImpl();
            ((JdbcDaoImpl) userDetailsService).setDataSource(dataSource);
        }
        return userDetailsService;
    }
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
	 
	 /* @Autowired
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
	} */
	
	
	
	
	
}

