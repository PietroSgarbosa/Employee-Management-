package com.employeemanagement.employeemanagement.dto;

import org.modelmapper.ModelMapper;
import com.employeemanagement.employeemanagement.entity.Training;

public class TrainingDTO {

	private Long id;
	
	private String description;
	
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	static ModelMapper getModelMapper() {
        return new ModelMapper();
    }
	
	public static TrainingDTO convertToDTO(Training entity) {
        return getModelMapper().map(entity, TrainingDTO.class);
    }
}
