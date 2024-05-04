package com.springBoot.ContactManagement.Controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.springBoot.ContactManagement.Entites.ContactDeatil;
import com.springBoot.ContactManagement.Entites.User;
import com.springBoot.ContactManagement.JpaRepository.ContactJpaRepository;
import com.springBoot.ContactManagement.JpaRepository.UserJpaRepository;

@RestController
public class SearchController {

	@Autowired
	UserJpaRepository userJpaRepository;
	
	@Autowired
	ContactJpaRepository contactJpaRepository;
	
	@GetMapping("/search/{keywords}")
	public ResponseEntity<?> searchUserByKeywords(@PathVariable("keywords")String searchKeyword,Principal principal){
		System.out.println("Keywords====>"+searchKeyword);
		User user = this.userJpaRepository.getUserDetailsByUserName(principal.getName());
		System.out.println(user.getUsername()+"====="+user.getUserId());
		List<ContactDeatil> searchResult = this.contactJpaRepository.findByContactNameAndUser(searchKeyword, user.getUserId());
		System.out.println("searchResult====>"+searchResult);
		return ResponseEntity.ok(searchResult);
	}
}
