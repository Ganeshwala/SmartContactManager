package com.springBoot.ContactManagement.Entites;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int userRoleId;
	private String userRole;
	
}
