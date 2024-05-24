package com.employeemanagement.employeemanagement.dto;

import org.modelmapper.ModelMapper;
import com.employeemanagement.employeemanagement.entity.Category;
import com.employeemanagement.employeemanagement.entity.Employee;



public class EmployeeDTO {
	
	private Long id;
	
	private String firstName;
	
	private String middleName;
	
	private String lastName;	
	
	private String cpf;
	
	private Category category;		

	//private List<EmployeeTraining> treinamentos;

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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}		
	
//	public List<EmployeeTraining> getTreinamentos() {
//		return treinamentos;
//	}
//
//	public void setTreinamentos(List<EmployeeTraining> treinamentos) {
//		this.treinamentos = treinamentos;
//	}
	
    static ModelMapper getModelMapper() {
        return new ModelMapper();
    }
    
    public static EmployeeDTO convertToDTO(Employee entity) {
        return getModelMapper().map(entity, EmployeeDTO.class);
    }

}
