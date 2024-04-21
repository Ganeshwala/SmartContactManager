package com.springBoot.ContactManagement.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.springBoot.ContactManagement.Services.UserDetailsServiceImp;

@Configuration
@EnableWebSecurity
public class MyConfiguration {

	@Bean
	public UserDetailsService getUserDetailsService() {
		return new UserDetailsServiceImp();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authoAuthenticationProvider = new DaoAuthenticationProvider();
		authoAuthenticationProvider.setUserDetailsService(this.getUserDetailsService());
		authoAuthenticationProvider.setPasswordEncoder(this.passwordEncoder());
		return authoAuthenticationProvider;
	}
	
	 @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
	 
	 
	
}
