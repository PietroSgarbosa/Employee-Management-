package com.employeemanagement.employeemanagement.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "FULLNAME", nullable = false)
	private String fullName;

	@Column(name = "CPF", nullable = false)
	private String cpf;
	
	@Column(name = "RG", nullable = false)
	private String rg;
	
	@Column(name = "ADMISSION_DATE", nullable = false)
	private Date admissionDate;
	
	@JoinColumn(name = "category_id")
	@ManyToOne
	private Category category;

	@OneToMany(mappedBy = "employee")
	private List<EmployeeTraining> trainings;
	
	@Column(name = "PHOTO")
	@Lob
	private byte[] photo;
	
	//COMENTADO PARA CORREÇÕES POSTERIORES
//	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
//	private List<OccupationalHealthCertificate> healthCertificates;
	
	@Column(name = "REGISTRATION_NUMBER")
	private Integer registrationNumber;

	public Employee() {
		
	}
	
	public Employee(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public Date getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public List<EmployeeTraining> getTrainings() {
		return trainings;
	}

	public void setTrainings(List<EmployeeTraining> trainings) {
		this.trainings = trainings;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

//	public List<OccupationalHealthCertificate> getHealthCertificates() {
//		return healthCertificates;
//	}
//
//	public void setHealthCertificates(List<OccupationalHealthCertificate> healthCertificates) {
//		this.healthCertificates = healthCertificates;
//	}

	public Integer getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(Integer registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	
}