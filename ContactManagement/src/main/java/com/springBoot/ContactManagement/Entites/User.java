package com.springBoot.ContactManagement.Entites;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "userDetails")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(generator = "userSeq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "userSeq", sequenceName = "User_Seq", initialValue = 100, allocationSize = 1)
	private int userId;
	
	@NotBlank(message = "User Name can not be empty")
	@Size(min = 5,max = 8,message = "User Name must be between 5 to 8 charactors")
	@Column(name = "loginUserName", unique = true, nullable = false)
	private String loginName;
	
	private String userNickName;
	
	@NotBlank(message = "Please enter Name here!!!!!")
	private String username;
	
	/*
	 * Password Validation
	 * 1) Password can not be blank
	 * 2) Password must be be between 5 to 15 char 
	 * 3)Minimum eight characters, at least one letter, one number and one special character:
	 */
	@NotBlank
	@Size(min = 5 ,/*max=15,*/message = "Password must br between 5 to 15 charactors")
	//@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",message = "Minimum eight characters, at least one letter, one number and one special character") 
	private String password;
	
	@NotBlank
	@Email(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")
	private String userEmail;
	
	/*
	 * Phone Number Validation
	 * 1) Number must have 10 digits
	 * 2) Number can not have any characters
	 */
	@Pattern(regexp = "^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4}$",message = "Please valided you Phone Number")
	private String phoneNumber;
	
	private Date createDate;
	
	private Date modifyDate;
	
	private String description;
	
	private String userImage;
	
	private boolean active;
	
	private String role;

	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "userObjId")
	private List<ContactDeatil> contacts;
	
	
	public enum RoleAssign{
		ADMIN("Admin"),
		SUBADM("Sub-Admin"),
		USER("User");
		
		private String assignRole;
		
		RoleAssign(String assignRole) {
			this.assignRole = assignRole;
		}
		
		public String getAssignRole() {
			return assignRole;
		}
	}
}
