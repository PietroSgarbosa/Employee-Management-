package com.employeemanagement.employeemanagement.dto;

import java.util.List;
import org.modelmapper.ModelMapper;
import com.employeemanagement.employeemanagement.entity.Category;
import com.employeemanagement.employeemanagement.entity.Employee;

public class EmployeeDTO {

	private Long id;

	private String firstName;

	private String middleName;

	private String lastName;

	private Category category;
	
	private Long categoryId;

	private String cpf;

	private List<Long> trainingsId;
	
	private List<EmployeeTrainingDTO> trainings;

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
	
	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
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

	public void setTrainingsId(List<Long> trainingsId) {
		this.trainingsId = trainingsId;
	}

	public List<EmployeeTrainingDTO> getTrainings() {
		return trainings;
	}

	public void setTrainings(List<EmployeeTrainingDTO> trainings) {
		this.trainings = trainings;
	}

	static ModelMapper getModelMapper() {
		return new ModelMapper();
	}

	public static EmployeeDTO convertToDTO(Employee entity) {
		return getModelMapper().map(entity, EmployeeDTO.class);
	}

}