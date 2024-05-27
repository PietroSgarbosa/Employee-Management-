package com.employeemanagement.employeemanagement.dto;

import org.modelmapper.ModelMapper;

import com.employeemanagement.employeemanagement.entity.Training;

public class TrainingDTO {
	
	private Long id;

	private String title;

	private String description;
	
	private String duration;
	
	//private List<EmployeeTrainingDTO> employees;
	

	public Long getID() {
		return id;
	}

	public void setID(Long id) {
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
	
	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	static ModelMapper getModelMapper() {
		return new ModelMapper();
	}

	public static TrainingDTO convertToDTO(Training entity) {
		return getModelMapper().map(entity, TrainingDTO.class);
	}


}
