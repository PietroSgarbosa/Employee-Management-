package com.employeemanagement.employeemanagement.dto;

import org.modelmapper.ModelMapper;

import com.employeemanagement.employeemanagement.entity.Training;

public class TrainingDTO {
	private Long id;

	private String name;

	private String description;

	public Long getID() {
		return id;
	}

	public void setID(Long iDTraining) {
		id = iDTraining;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	static ModelMapper getModelMapper() {
		return new ModelMapper();
	}

	public static TrainingDTO convertToDTO(Training entity) {
		return getModelMapper().map(entity, TrainingDTO.class);
	}


}
