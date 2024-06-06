package com.employeemanagement.employeemanagement.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.employeemanagement.employeemanagement.dto.EmployeeDTO;
import com.employeemanagement.employeemanagement.entity.Employee;

@Component
public class EmployeeMapper {
	
	@Autowired
	private CategoryMapper categoryMapper;

	public Employee covertToEntity(EmployeeDTO dto) {
		Employee entity = new Employee();
		entity.setId(dto.getId());
		entity.setFirstName(dto.getFirstName());
		entity.setMiddleName(dto.getMiddleName());
		entity.setLastName(dto.getLastName());
		entity.setCpf(dto.getCpf());
		return entity;
	}

	public CategoryMapper getCategoryMapper() {
		return categoryMapper;
	}
}