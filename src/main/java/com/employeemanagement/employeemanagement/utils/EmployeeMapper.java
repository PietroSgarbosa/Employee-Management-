package com.employeemanagement.employeemanagement.utils;

import java.util.Base64;

import org.springframework.stereotype.Component;

import com.employeemanagement.employeemanagement.dto.EmployeeDTO;
import com.employeemanagement.employeemanagement.entity.Employee;

@Component
public class EmployeeMapper {

	public static Employee covertToEntity(EmployeeDTO dto) {
		Employee entity = new Employee();
		entity.setId(dto.getId());
		entity.setFirstName(dto.getFirstName());
		entity.setMiddleName(dto.getMiddleName());
		entity.setLastName(dto.getLastName());
		entity.setCpf(dto.getCpf());
		if(dto.getPhoto() != null) {
			entity.setPhoto(Base64.getDecoder().decode(dto.getPhoto()));
		}

		return entity;
	}

}