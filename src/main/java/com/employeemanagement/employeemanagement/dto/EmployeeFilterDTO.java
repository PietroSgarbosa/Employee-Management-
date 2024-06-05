package com.employeemanagement.employeemanagement.dto;

import org.modelmapper.ModelMapper;
import com.employeemanagement.employeemanagement.entity.Employee;

public class EmployeeFilterDTO {
	
	private String firstName;
	private String middleName;
	private String lastName;
	private String cpf;
	private Long trainingsId;
			
	
	

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

	public Long getTrainingsId() {
		return trainingsId;
	}

	public void setTrainingsId(Long trainingsId) {
		this.trainingsId = trainingsId;
	}

	static ModelMapper getModelMapper() {
		return new ModelMapper();
	}

	public static EmployeeFilterDTO convertToDTO(Employee entity) {
		return getModelMapper().map(entity, EmployeeFilterDTO.class);
	}

}
