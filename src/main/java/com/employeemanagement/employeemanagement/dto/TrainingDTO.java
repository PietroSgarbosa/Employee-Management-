package com.employeemanagement.employeemanagement.dto;

import org.modelmapper.ModelMapper;

import com.employeemanagement.employeemanagement.entity.Training;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class TrainingDTO {

	@JsonIgnore
	private Long id;

	private String title;

	private String description;
	
	@JsonIgnore
	private StatusDTO status;
	
	@JsonIgnore
	private Long categoryId;
	
	private CategoryDTO categoryDTO;

	public StatusDTO getStatus() {
		return status;
	}

	public void setStatus(StatusDTO status) {
		this.status = status;
	}

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

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public CategoryDTO getCategoryDTO() {
		return categoryDTO;
	}

	public void setCategoryDTO(CategoryDTO categoryDTO) {
		this.categoryDTO = categoryDTO;
	}

	static ModelMapper getModelMapper() {
		return new ModelMapper();
	}

	public static TrainingDTO convertToDTO(Training entity) {
		return getModelMapper().map(entity, TrainingDTO.class);
	}

}