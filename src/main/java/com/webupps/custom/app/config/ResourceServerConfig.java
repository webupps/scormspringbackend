package com.webupps.custom.app.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


//@Configuration
//@EnableResourceServer
public class ResourceServerConfig {
	
	    private static final String RESOURCE_ID = "resource_id";

	    public void configure(ResourceServerSecurityConfigurer resources) {
	        resources.resourceId(RESOURCE_ID).stateless(false);
	    }

	    public void configure(HttpSecurity http) throws Exception {
	        /*http.
	            anonymous().disable()
	            .authorizeRequests()
	            .antMatchers("/api/**").authenticated()
	            .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());*/
	    	http 
			    .authorizeRequests()
			    .antMatchers("/v1/admin/**").hasRole("ADMIN")
				.antMatchers("/v1/greeting/hello").hasRole("USER")
				.antMatchers(HttpMethod.POST,"/v1/users/registration").permitAll()
				.antMatchers("/logout").permitAll()
				.antMatchers("/").permitAll()
		        .anyRequest().authenticated()
		        .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
		    /*.and().csrf().disable()
		    .formLogin()
		        .and()
		    .httpBasic().disable();*/
	    }
}
