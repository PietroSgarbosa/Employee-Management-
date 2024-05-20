package com.employeemanagement.employeemanagement.dto;

import org.modelmapper.ModelMapper;

import com.employeemanagement.employeemanagement.entity.Collaborator;

public class CollaboratorDTO {
	
	private Long id;
	
	private String firstName;
	
	private String middleName;
	
	private String lastName;
	
	private String category;
	
	private String cpf;

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
	
    static ModelMapper getModelMapper() {
        return new ModelMapper();
    }
    
    public static CollaboratorDTO convertToDTO(Collaborator entity) {
        return getModelMapper().map(entity, CollaboratorDTO.class);
    }

}
