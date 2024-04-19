package com.springBoot.ContactManagement;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CMController {

	@GetMapping("/")
	public String getWelcomePage(Model model) {
		model.addAttribute("title", "Smart Contact Management Application");
		return "welcome";
	}
}
