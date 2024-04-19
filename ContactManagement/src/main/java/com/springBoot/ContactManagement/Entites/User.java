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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
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
	@SequenceGenerator(name = "userSeq", sequenceName = "User_Seq", initialValue = 100, allocationSize = 1)
	@GeneratedValue(generator = "userSeq", strategy = GenerationType.SEQUENCE)
	private int userId;
	@Column(name = "loginUserName", unique = true, nullable = false)
	private String loginName;
	private String userNickName;
	private String username;
	private String userEmail;
	private double phoneNumber;
	private Date createDate;
	private Date modifyDate;
	private String description;
	private String userImage;
	private boolean active;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private UserRole role;

	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "userObjId")
	private List<ContactDeatil> contacts;
}
