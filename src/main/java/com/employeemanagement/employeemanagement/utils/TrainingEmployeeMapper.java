package com.employeemanagement.employeemanagement.utils;

import org.springframework.stereotype.Component;

import com.employeemanagement.employeemanagement.dto.TrainingEmployeeDTO;
import com.employeemanagement.employeemanagement.entity.TrainingEmployee;

@Component
public class TrainingEmployeeMapper {
	
	public TrainingEmployee covertToEntity(TrainingEmployeeDTO dto) {
		TrainingEmployee entity = new TrainingEmployee();

		
		return entity;
	}

}
