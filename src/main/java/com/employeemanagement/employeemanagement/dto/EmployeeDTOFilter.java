package com.employeemanagement.employeemanagement.dto;

import java.util.List;

import org.modelmapper.ModelMapper;

import com.employeemanagement.employeemanagement.entity.Employee;
import com.employeemanagement.employeemanagement.entity.EmployeeTraining;

public class EmployeeDTOFilter {

	private Long id;

	private String firstName;

	private String middleName;

	private String lastName;

	private String cpf;

	private CategoryDTO category;

	private List<EmployeeTraining> trainings;

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

	public CategoryDTO getCategory() {
		return category;
	}

	public void setCategory(CategoryDTO category) {
		this.category = category;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	static ModelMapper getModelMapper() {
		return new ModelMapper();
	}

	public static EmployeeDTOFilter convertToDTO(Employee entity) {
		return getModelMapper().map(entity, EmployeeDTOFilter.class);
	}

	public List<EmployeeTraining> getTrainings() {
		return trainings;
	}

	public void setTrainings(List<EmployeeTraining> trainings) {
		this.trainings = trainings;
	}
}
