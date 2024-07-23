package com.employeemanagement.employeemanagement.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "HEALTH_CERTIFICATE")
public class OccupationalHealthCertificate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonIgnore
	@JoinColumn(name = "employee_id")
	@ManyToOne
	private Employee employee;
	
	@Column(name = "DATE")
	private Date date;

	@ManyToOne
    @JoinColumn(name = "HEALTH_CERTIFICATE_ASO_TYPE_ID")
	private OccupationalHealthCertificateType occupationHealthCertificateType;
	
	@Column(name= "IS_ACCOMPLISHED")
	private boolean isAccomplished;

	@ManyToOne
    @JoinColumn(name = "current_category_id")
	private Category currentCategory;
	
    @ManyToOne
	@JoinColumn(name = "new_category_id")
	private Category newCategory;

	public OccupationalHealthCertificate() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public OccupationalHealthCertificateType getOccupationHealthCertificateType() {
		return occupationHealthCertificateType;
	}

	public void setOccupationHealthCertificateType(OccupationalHealthCertificateType occupationHealthCertificateType) {
		this.occupationHealthCertificateType = occupationHealthCertificateType;
	}

	public void setAccomplished(boolean isAccomplished) {
		this.isAccomplished = isAccomplished;
	}

	public boolean getIsAccomplished() {
		return isAccomplished;
	}

	public void setIsAccomplished(boolean isAccomplished) {
		this.isAccomplished = isAccomplished;
	}

	public Category getCurrentCategory() {
		return currentCategory;
	}

	public void setCurrentCategory(Category currentCategory) {
		this.currentCategory = currentCategory;
	}

	public Category getNewCategory() {
		return newCategory;
	}

	public void setNewCategory(Category newCategory) {
		this.newCategory = newCategory;
	}

}
