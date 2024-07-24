package com.employeemanagement.employeemanagement.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "HEALTH_CLINIC")
public class HealthClinic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "CLINIC_NAME")
	private String name;
	
	@Column(name = "CLINIC_STREET")
	private String street;
	
	@Column(name = "CLINIC_CITY")
	private String city;
	
	//Postal Code
	@Column(name = "CLINIC_ZIPCODE")
	private String zipCode;
	
	@Column(name = "CLINIC_COUNTRY")
	private String country;
	
	@Column(name = "CLINIC_PHONE")
	private String phone;
	
	@Column(name = "CLINIC_EMAIL")
	private String email;
	
	@Column(name = "CLINIC_REGISTRATION_NUMBER")
	private Long registrationNumber;
	
	@OneToMany(mappedBy = "healthClinic")
	private List<OccupationalHealthCertificate> occupationalHealthCertificateList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(Long registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public List<OccupationalHealthCertificate> getOccupationalHealthCertificateList() {
		return occupationalHealthCertificateList;
	}

	public void setOccupationalHealthCertificateList(
			List<OccupationalHealthCertificate> occupationalHealthCertificateList) {
		this.occupationalHealthCertificateList = occupationalHealthCertificateList;
	}
	
}
