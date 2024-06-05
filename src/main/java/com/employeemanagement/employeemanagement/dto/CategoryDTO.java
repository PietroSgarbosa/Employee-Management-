package com.employeemanagement.employeemanagement.dto;

import java.util.List;
import org.modelmapper.ModelMapper;
import com.employeemanagement.employeemanagement.entity.Category;


public class CategoryDTO {

	private Long id;

	private String description;
	
	private List<EmployeeDTO> employess;

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

	public List<EmployeeDTO> getEmployess() {
		return employess;
	}

	public void setEmployess(List<EmployeeDTO> employess) {
		this.employess = employess;
	}

	static ModelMapper getModelMapper() {
		return new ModelMapper();
	}

	public static CategoryDTO convertToDTO(Category category) {
		return getModelMapper().map(category, CategoryDTO.class);
	}
}
