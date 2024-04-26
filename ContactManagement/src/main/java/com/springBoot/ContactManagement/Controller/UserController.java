package com.springBoot.ContactManagement.Controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.springBoot.ContactManagement.Entites.ContactDeatil;
import com.springBoot.ContactManagement.Entites.User;
import com.springBoot.ContactManagement.Helper.MessageHelper;
import com.springBoot.ContactManagement.JpaRepository.ContactJpaRepository;
import com.springBoot.ContactManagement.JpaRepository.UserJpaRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserJpaRepository userJpaRepository;
	
	@Autowired
	private ContactJpaRepository contactRepo;
	
	//common method which is call every time when this controller is call (kind of default method for all others method)
	@ModelAttribute
	private void UserController(Model model,Principal principal) {
		String loginName = principal.getName();
		System.out.println("User page"+loginName);
		User userDetailsByUserName = getUserDetailt(loginName);
		model.addAttribute("User", userDetailsByUserName);
	}
	
	private User getUserDetailt(String loginName) {
		User userDetailsByUserName = this.userJpaRepository.getUserDetailsByUserName(loginName);
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
	public String saveContact(@ModelAttribute("contactInfo") ContactDeatil conDetails,@RequestParam("profileImg")MultipartFile image , Model md,Principal principal,HttpSession session) {
		//TODO: process POST request
		try {
			System.out.println(conDetails.toString());
			User userDetailsByUserName = this.userJpaRepository.getUserDetailsByUserName(principal.getName());
			
			if(image.isEmpty()) {
				conDetails.setContactImg("default.jpeg");
			}
			else {
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
				
				session.setAttribute("message", new MessageHelper("Contact Added Successfully!!!","success"));
			}
		}catch(Exception e) {
			System.out.println(e);
			session.setAttribute("message", new MessageHelper("Something want worng","danger"));
		}
		
		
		return "UserPages/addContact";
	}
	/**
	 * 
	 * per page suppose we want to show 'n' contact e.g here we show 5 person per page
	 * initial page number is 0 -> this will set as current page
	 * 
	 * @param md
	 * @param principal
	 * @return
	 */
	@GetMapping("/showContacts/{page}")
	public String ShowContacts(@PathVariable("page") Integer crntPage, Model md,Principal principal) {
		md.addAttribute("title", "Show Contact");
		User loginUser = getUserDetailt(principal.getName());
		//md.addAttribute("ContactInfos", loginUser.getContacts());*/
		
		Pageable pageRequest = PageRequest.of(crntPage, 2);
		
		Page<ContactDeatil> contactList = this.contactRepo.findContactByUserId(loginUser.getUserId(),pageRequest);
		md.addAttribute("contactsList", contactList);
		md.addAttribute("currentPage", crntPage);
		md.addAttribute("totalPages", contactList.getTotalPages());
		
		return "UserPages/displayContacts";
	}
	
	@GetMapping("/viewDeatis/{contactId}")
	public String showContactDetail(@PathVariable("contactId") Long cId,Model md,Principal principal) {
		System.out.println("contactId"+cId);
		ContactDeatil contactInfo = this.contactRepo.findAllByContactId(cId);
		User loginUser = getUserDetailt(principal.getName());
		if(contactInfo!=null && loginUser.getUserId() == contactInfo.getUserObj().getUserId())
			md.addAttribute("contactInfo", contactInfo);
		
		return "UserPages/contactDetail";
	}
	
}
