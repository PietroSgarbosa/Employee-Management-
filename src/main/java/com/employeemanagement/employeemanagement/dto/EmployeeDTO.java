package com.employeemanagement.employeemanagement.dto;

import java.util.List;
import org.modelmapper.ModelMapper;
import com.employeemanagement.employeemanagement.entity.Employee;

public class EmployeeDTO {
	
	private Long id;
	
	private String firstName;
	
	private String middleName;
	
	private String lastName;
	
	private Long category;
	
	private String cpf;
	
	private List<Long> trainingList;

	private List<TrainingEmployeeDTO> listTrainingEmployeeDTO;
	
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

	public Long getCategory() {
		return category;
	}

	public void setCategory(Long category) {
		this.category = category;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public List<Long> getTrainingList() {
		return trainingList;
	}

	public void setTrainingList(List<Long> trainingList) {
		this.trainingList = trainingList;
	}

	public List<TrainingEmployeeDTO> getListTrainingEmployeeDTO() {
		return listTrainingEmployeeDTO;
	}

	public void setListTrainingEmployeeDTO(List<TrainingEmployeeDTO> listTrainingEmployeeDTO) {
		this.listTrainingEmployeeDTO = listTrainingEmployeeDTO;
	}

	static ModelMapper getModelMapper() {
        return new ModelMapper();
    }
    
    public static EmployeeDTO convertToDTO(Employee entity) {
        return getModelMapper().map(entity, EmployeeDTO.class);
    }

}
