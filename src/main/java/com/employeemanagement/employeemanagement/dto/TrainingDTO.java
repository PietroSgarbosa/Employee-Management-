package com.employeemanagement.employeemanagement.dto;

import java.util.List;

import org.modelmapper.ModelMapper;

import com.employeemanagement.employeemanagement.entity.Training;
import com.employeemanagement.employeemanagement.entity.TrainingEmployee;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class TrainingDTO {
	
	private Long id;

	private String title;

	private String description;
	
	@JsonIgnore
	private List<TrainingEmployeeDTO> employees;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<TrainingEmployeeDTO> getEmployees() {
		return employees;
	}

	public void setEmployees(List<TrainingEmployeeDTO> employees) {
		this.employees = employees;
	}

	static ModelMapper getModelMapper() {
		return new ModelMapper();
	}

	public static TrainingDTO convertToDTO(Training entity) {
		return getModelMapper().map(entity, TrainingDTO.class);
	}

}
