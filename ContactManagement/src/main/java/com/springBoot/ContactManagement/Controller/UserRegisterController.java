package com.springBoot.ContactManagement.Controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springBoot.ContactManagement.Entites.User;
import com.springBoot.ContactManagement.Entites.User.RoleAssign;
import com.springBoot.ContactManagement.Helper.MessageHelper;
import com.springBoot.ContactManagement.JpaRepository.UserJpaRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserRegisterController {
	
	@Autowired
	UserJpaRepository userRepo;

	@PostMapping("/doRegister")
	public String getUserInformation(@Valid @ModelAttribute("user") User userInfo,BindingResult result,
			@RequestParam(value = "agreement", defaultValue = "false") boolean agreementValue, Model model,HttpSession session) {
		
		try {
				
				if(!agreementValue) {
					throw new Exception("Did not check Terms and Conditions");
				}
				if(result.hasErrors()) {
					System.out.println("Server side Error occurred!!!"+result.toString());
					model.addAttribute("user", userInfo);
					return"userSignUp";
				}
				System.out.println(userInfo.toString());
				System.out.println(agreementValue);
				//model.addAttribute("user", userInfo);
				//	Create new user / storing information in user table
				userInfo.setActive(true);
				userInfo.setCreateDate(new Date());
				userInfo.setModifyDate(new Date());
				userInfo.setRole(RoleAssign.USER.getAssignRole());
				
				userRepo.save(userInfo);
				
				model.addAttribute("user", new User());
				session.setAttribute("message", new MessageHelper("Successfull Registered","alert-success"));
				model.addAttribute("session", session);
				return "userSignUp";
		}catch(Exception e) {
			model.addAttribute("user", userInfo);
			session.setAttribute("message", new MessageHelper("Something Went Worng","alert-danger"));
			model.addAttribute("session", session);
			return "userSignUp";
		}
		
	}
}
