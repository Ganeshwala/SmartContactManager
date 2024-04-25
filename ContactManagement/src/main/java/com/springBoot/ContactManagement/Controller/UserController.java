package com.springBoot.ContactManagement.Controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.springBoot.ContactManagement.Entites.ContactDeatil;
import com.springBoot.ContactManagement.Entites.User;
import com.springBoot.ContactManagement.JpaRepository.UserJpaRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserJpaRepository userJpaRepository;
	
	//common method which is call every time when this controller is call (kind of default method for all others method)
	@ModelAttribute
	private User getUserDetails(Model model,Principal principal) {
		String loginName = principal.getName();
		System.out.println("User page"+loginName);
		User userDetailsByUserName = this.userJpaRepository.getUserDetailsByUserName(loginName);
		model.addAttribute("User", userDetailsByUserName);
		return userDetailsByUserName;
	}
	
	@RequestMapping("/dashBoard")
	public String getUserDashBoard(Model model,Principal principal) {
		model.addAttribute("title", "User Loging");
		return"UserPages/userDashBoard";
	}
	
	@GetMapping("/addContacts")
	public String addContacts(Model md,Principal principal) {
		md.addAttribute("title", "Add Contact");
		md.addAttribute("contactInfo",new ContactDeatil());
		return "UserPages/addContact";
	}
	
	@PostMapping("/saveContacts")
	public String saveContact(@ModelAttribute("contactInfo") ContactDeatil conDetails,@RequestParam("profileImg")MultipartFile image , Model md,Principal principal) {
		//TODO: process POST request
		try {
			System.out.println(conDetails.toString());
			User userDetailsByUserName = this.userJpaRepository.getUserDetailsByUserName(principal.getName());
			
			// processing and uploading file on location
			conDetails.setContactImg(image.getOriginalFilename());
			File saveImgLocation = new ClassPathResource("static/IMG").getFile();
			
			Path path = Paths.get(saveImgLocation.getAbsolutePath()+File.separator+image.getOriginalFilename());
			
			Files.copy(image.getInputStream(), path,StandardCopyOption.REPLACE_EXISTING);
			
			conDetails.setUserObj(userDetailsByUserName);
			userDetailsByUserName.getContacts().add(conDetails);
			this.userJpaRepository.save(userDetailsByUserName);
			System.out.println("Successful added contact");
			md.addAttribute("title", "Add Contact");
		}catch(Exception e) {
			System.out.println(e);
		}
		
		
		return "UserPages/addContact";
	}
	
}
