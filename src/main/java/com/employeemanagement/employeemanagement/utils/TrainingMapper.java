package com.employeemanagement.employeemanagement.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.employeemanagement.employeemanagement.dto.EmployeeTrainingDTO;
import com.employeemanagement.employeemanagement.dto.TrainingDTO;
import com.employeemanagement.employeemanagement.entity.EmployeeTraining;
import com.employeemanagement.employeemanagement.entity.Training;

@Component
public class TrainingMapper {

	public Training covertToEntity(TrainingDTO dto) {
		Training entity = new Training();
		entity.setId(dto.getId());
		entity.setTitle(dto.getTitle());
		entity.setDescription(dto.getDescription());
		
		return entity;
	}
}
