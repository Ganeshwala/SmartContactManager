package com.springBoot.ContactManagement.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

	
	@RequestMapping("/dashBoard")
	public String getUserDashBoard(Model model) {
		System.out.println("User page");
		model.addAttribute("title", "User Loging");
		return"UserPages/userDashBoard";
	}
}
