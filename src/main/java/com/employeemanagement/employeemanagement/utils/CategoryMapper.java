package com.employeemanagement.employeemanagement.utils;

import org.springframework.stereotype.Component;

import com.employeemanagement.employeemanagement.dto.CategoryDTO;
import com.employeemanagement.employeemanagement.entity.Category;


@Component
public class CategoryMapper {
	
	public Category covertToEntity(CategoryDTO dto) {
		Category entity = new Category();
		entity.setId(dto.getId());		
		entity.setDescription(dto.getDescription());
		
		return entity;
	}

}
