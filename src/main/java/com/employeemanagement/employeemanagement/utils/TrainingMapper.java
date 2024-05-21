package com.employeemanagement.employeemanagement.utils;

import org.springframework.stereotype.Component;

import com.employeemanagement.employeemanagement.dto.EmployeeDTO;
import com.employeemanagement.employeemanagement.dto.TrainingDTO;
import com.employeemanagement.employeemanagement.entity.Employee;
import com.employeemanagement.employeemanagement.entity.Training;

@Component
public class TrainingMapper {
	
	public Training covertToEntity(TrainingDTO dto) {
		Training entity = new Training();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());		
		
		return entity;
	}

}
