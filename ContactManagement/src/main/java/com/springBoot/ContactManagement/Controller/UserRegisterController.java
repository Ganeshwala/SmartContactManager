package com.springBoot.ContactManagement.Controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springBoot.ContactManagement.Entites.User;
import com.springBoot.ContactManagement.Entites.User.RoleAssign;
import com.springBoot.ContactManagement.JpaRepository.UserJpaRepository;

@Controller
public class UserRegisterController {
	
	@Autowired
	UserJpaRepository userRepo;

	@PostMapping("/doRegister")
	public String getUserInformation(@ModelAttribute("user") User userInfo,
			@RequestParam(value = "agreement", defaultValue = "false") boolean agreementValue, Model model) {
		System.out.println(userInfo.toString());
		System.out.println(agreementValue);
		//model.addAttribute("user", userInfo);
		//	Create new user / storing information in user table
		userInfo.setActive(true);
		userInfo.setCreateDate(new Date());
		userInfo.setModifyDate(new Date());
		userInfo.setRole(RoleAssign.USER.getAssignRole());
		
		userRepo.save(userInfo);
		
		return "/userSignUp";
	}
}
