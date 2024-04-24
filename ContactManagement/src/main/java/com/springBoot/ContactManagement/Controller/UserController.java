package com.springBoot.ContactManagement.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springBoot.ContactManagement.Entites.User;
import com.springBoot.ContactManagement.JpaRepository.UserJpaRepository;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserJpaRepository userJpaRepository;
	
	@RequestMapping("/dashBoard")
	public String getUserDashBoard(Model model,Principal principal) {
		String name = principal.getName();
		System.out.println("User page"+name);
		
		User userDetailsByUserName = this.userJpaRepository.getUserDetailsByUserName(name);
		
		model.addAttribute("User", userDetailsByUserName);
		
		model.addAttribute("title", "User Loging");
		return"UserPages/userDashBoard";
	}
}
