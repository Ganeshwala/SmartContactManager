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

	public long getContactId() {
		return contactId;
	}

	public void setContactId(long contactId) {
		this.contactId = contactId;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getContactOccupation() {
		return contactOccupation;
	}

	public void setContactOccupation(String contactOccupation) {
		this.contactOccupation = contactOccupation;
	}

	public String getContactImg() {
		return contactImg;
	}

	public void setContactImg(String contactImg) {
		this.contactImg = contactImg;
	}

	public double getContachPhone() {
		return contachPhone;
	}

	public void setContachPhone(double contachPhone) {
		this.contachPhone = contachPhone;
	}

	public String getContactAbout() {
		return contactAbout;
	}

	public void setContactAbout(String contactAbout) {
		this.contactAbout = contactAbout;
	}

	public User getUserObj() {
		return userObj;
	}

	public void setUserObj(User userObj) {
		this.userObj = userObj;
	}
	
	
	
}
