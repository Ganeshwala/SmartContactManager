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
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	
	@GetMapping("/EditProfile/{userId}/{profileImg}")
	public ResponseEntity<?> postMethodName(@PathVariable("userId")int uId,@PathVariable("profileImg")MultipartFile image,Model md,Principal principal) {
		//TODO: process POST request
		System.out.println("User ID====>"+uId+"<========>");
		User userDetailt = this.userJpaRepository.getUserDetailsByUserName(principal.getName());
		try {
			if(userDetailt !=null && userDetailt.getUserId() == uId) {
				
				File deleteImgLocation = new ClassPathResource("static/IMG").getFile();
				File fileRomve = new File(deleteImgLocation, userDetailt.getUserImage());
				if(!userDetailt.getUserImage().equalsIgnoreCase("default.jpeg") ) {
					fileRomve.delete();
				}
				
				/*File saveImgLocation = new ClassPathResource("static/IMG").getFile();
				
				Path path = Paths.get(saveImgLocation.getAbsolutePath()+File.separator+image.getOriginalFilename());
				
				Files.copy(image.getInputStream(), path,StandardCopyOption.REPLACE_EXISTING);
				
				userDetailt.setUserImage(image.getOriginalFilename());*/
				
				this.userJpaRepository.save(userDetailt);
				
			}
		}catch(Exception e) {
			
		}
		
		return ResponseEntity.ok(userDetailt);
	}
}
