package com.employeemanagement.employeemanagement.utils;

import org.springframework.stereotype.Component;
import com.employeemanagement.employeemanagement.dto.EmployeeDTO;
import com.employeemanagement.employeemanagement.entity.Employee;

@Component
public class EmployeeMapper {

	public Employee covertToEntity(EmployeeDTO dto) {
		Employee entity = new Employee();
		entity.setId(dto.getId());
		entity.setFirstName(dto.getFirstName());
		entity.setMiddleName(dto.getMiddleName());
		entity.setLastName(dto.getLastName());
		entity.setCpf(dto.getCpf());
		entity.setCategory(dto.getCategory());
		return entity;
	}

}