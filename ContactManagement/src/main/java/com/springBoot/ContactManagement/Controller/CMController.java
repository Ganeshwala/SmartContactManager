package com.springBoot.ContactManagement.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.springBoot.ContactManagement.Entites.User;

@Controller
public class CMController {

	@GetMapping("/SmartContactManager")
	public String getWelcomePage(Model model) {
		model.addAttribute("title", "Smart Contact Management Application");
		return "welcome";
	}
	/**
	 * User Registration Page link
	 * @param model
	 * @return
	 */
	@GetMapping("/userSignUp")
	public String doUserSignUp(Model model) {
		model.addAttribute("title", "Register User - Smart Contact Management Application");
		model.addAttribute("user", new User());
		return "userSignUp";
	}
}
