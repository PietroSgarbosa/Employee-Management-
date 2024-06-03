package com.employeemanagement.employeemanagement.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "firstName", nullable = false)
	private String firstName;
	
	@Column(name = "middleName")
	private String middleName;
	
	@Column(name = "lastName", nullable = false)
	private String lastName;
	
	@Column(name = "cpf", nullable = false)
	private String cpf;
	
	@ManyToOne	      
	@JoinColumn(name ="category")
	private Category category;
	
	
	@OneToMany(mappedBy = "employee")
	private  List<EmployeeTraining> trainings;	
	
	public Employee () {		
	}	

	public Employee(Long id, String firstName, String middleName, String lastName, String cpf, Category category, List<EmployeeTraining> trainings) {		
		this.id = id;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.cpf = cpf;
		this.category = category;		
		this.trainings = trainings;
	}

	public Employee (Long id) {
		this.id = id;
	}		

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	
}
