package com.springBoot.ContactManagement.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.springBoot.ContactManagement.Configuration.CustomUserDetails;
import com.springBoot.ContactManagement.Entites.User;
import com.springBoot.ContactManagement.JpaRepository.UserJpaRepository;

public class UserDetailsServiceImp implements UserDetailsService{

	@Autowired
	UserJpaRepository userJpa;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User userDetails = userJpa.getUserDetailsByUserName(username);
		if(userDetails == null) {
			throw new UsernameNotFoundException("Could not found User");
		}
		CustomUserDetails customUserDetails = new CustomUserDetails(userDetails);
		return customUserDetails;
	}

}
