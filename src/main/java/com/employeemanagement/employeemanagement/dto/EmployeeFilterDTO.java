package com.employeemanagement.employeemanagement.dto;

public class EmployeeFilterDTO {

	private Long id;

	private String firstName;

	private String middleName;

	private String lastName;

	private String category;

	private String cpf;

	private Long training;

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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Long getTraining() {
		return training;
	}

	public void setTraining(Long training) {
		this.training = training;
	}
}
