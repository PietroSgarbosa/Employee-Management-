package com.employeemanagement.employeemanagement.dto;

import java.util.List;

import org.modelmapper.ModelMapper;

import com.employeemanagement.employeemanagement.entity.Employee;

public class EmployeeFilterDTO {
	
	private String firstName;
	private String middleName;
	private String lastName;
	private String cpf;
	private List<Long> trainingsId;
	
		
	
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public List<Long> getTrainingsId() {
		return trainingsId;
	}

	public void setTrainings(List<Long> trainings) {
		this.trainingsId = trainings;
	}

	static ModelMapper getModelMapper() {
		return new ModelMapper();
	}

	public static EmployeeFilterDTO convertToDTO(Employee entity) {
		return getModelMapper().map(entity, EmployeeFilterDTO.class);
	}

}
