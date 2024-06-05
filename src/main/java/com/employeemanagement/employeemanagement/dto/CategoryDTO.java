package com.employeemanagement.employeemanagement.dto;

import java.util.List;

import org.modelmapper.ModelMapper;

import com.employeemanagement.employeemanagement.entity.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class CategoryDTO {

	private Long id;

	private String description;

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

	static ModelMapper getModelMapper() {
		return new ModelMapper();
	}

	public static CategoryDTO convertToDTO(Category category) {
		return getModelMapper().map(category, CategoryDTO.class);
	}
}
