/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 06 June 2019
 * Modified Date	: 06 June 2019	
 * Comments			: 
 */


package com.accelerate.dash.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "students")
public class Student {
    
    @Id
    private String id;

    private String admissionNumber;
    private String name;
    private String mobile;
    private String email;
    private String dob;
    private String guardianName;
    private String guardianMobile;
    private String emergencyContact;
    private String emergencyContactMobile;
    private String addressLineOne;
    private String addressLineTwo;
    private String postalCode;
    private String city;
    private String state;
    private boolean isPhotoUploaded;
    private String adminCode;
    private String entryDate;
	private String admissionDate;
	
	public Student() {}
    
	public Student(String id, String admissionNumber, String name, String mobile, String email, String dob,
			String guardianName, String guardianMobile, String emergencyContact, String emergencyContactMobile,
			String addressLineOne, String addressLineTwo, String postalCode, String city, String state,
			boolean isPhotoUploaded, String adminCode, String entryDate, String admissionDate) {
		this.admissionNumber = admissionNumber;
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.dob = dob;
		this.guardianName = guardianName;
		this.guardianMobile = guardianMobile;
		this.emergencyContact = emergencyContact;
		this.emergencyContactMobile = emergencyContactMobile;
		this.addressLineOne = addressLineOne;
		this.addressLineTwo = addressLineTwo;
		this.postalCode = postalCode;
		this.city = city;
		this.state = state;
		this.isPhotoUploaded = isPhotoUploaded;
		this.adminCode = adminCode;
		this.entryDate = entryDate;
		this.admissionDate = admissionDate;
    }
    
	public String getId() {
		return id;
    }
    
	public void setId(String id) {
		this.id = id;
    }
    
	public String getAdmissionNumber() {
		return admissionNumber;
    }
    
	public void setAdmissionNumber(String admissionNumber) {
		this.admissionNumber = admissionNumber;
    }
    
	public String getName() {
		return name;
    }
    
	public void setName(String name) {
		this.name = name;
    }
    
	public String getMobile() {
		return mobile;
    }
    
	public void setMobile(String mobile) {
		this.mobile = mobile;
    }
    
	public String getEmail() {
		return email;
    }
    
	public void setEmail(String email) {
		this.email = email;
    }
    
	public String getDob() {
		return dob;
    }
    
	public void setDob(String dob) {
		this.dob = dob;
    }
    
	public String getGuardianName() {
		return guardianName;
    }
    
	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
    }
    
	public String getGuardianMobile() {
		return guardianMobile;
    }
    
	public void setGuardianMobile(String guardianMobile) {
		this.guardianMobile = guardianMobile;
    }
    
	public String getEmergencyContact() {
		return emergencyContact;
    }
    
	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
    }
    
	public String getEmergencyContactMobile() {
		return emergencyContactMobile;
    }
    
	public void setEmergencyContactMobile(String emergencyContactMobile) {
		this.emergencyContactMobile = emergencyContactMobile;
    }
    
	public String getAddressLineOne() {
		return addressLineOne;
    }
    
	public void setAddressLineOne(String addressLineOne) {
		this.addressLineOne = addressLineOne;
    }
    
	public String getAddressLineTwo() {
		return addressLineTwo;
    }
    
	public void setAddressLineTwo(String addressLineTwo) {
		this.addressLineTwo = addressLineTwo;
    }
    
	public String getPostalCode() {
		return postalCode;
    }
    
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
    }
    
	public String getCity() {
		return city;
    }
    
	public void setCity(String city) {
		this.city = city;
    }
    
	public String getState() {
		return state;
    }
    
	public void setState(String state) {
		this.state = state;
    }
    
	public boolean isPhotoUploaded() {
		return isPhotoUploaded;
    }
    
	public void setPhotoUploaded(boolean isPhotoUploaded) {
		this.isPhotoUploaded = isPhotoUploaded;
    }
    
	public String getAdminCode() {
		return adminCode;
    }
    
	public void setAdminCode(String adminCode) {
		this.adminCode = adminCode;
    }
    
	public String getEntryDate() {
		return entryDate;
    }
    
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
    }
    
	public String getAdmissionDate() {
		return admissionDate;
    }
    
	public void setAdmissionDate(String admissionDate) {
		this.admissionDate = admissionDate;
	}
}
