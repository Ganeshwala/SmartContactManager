package com.springBoot.ContactManagement.Entites;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactDeatil {

	@Id
	@SequenceGenerator(name = "contactSeq",sequenceName = "conDetSeq",initialValue = 1,allocationSize = 1)
	@GeneratedValue(generator = "contactSeq",strategy = GenerationType.SEQUENCE)
	private long contactId;
	private String contactName;
	private String contactEmail;
	private String contactOccupation;
	private String contactImg;
	private double contachPhone;
	@Column(length = 5000)
	private String contactAbout;
	
	@ManyToOne
	@JoinColumn(name = "user_Id",referencedColumnName = "userId")
	private User userObj;
	
}
