package com.employeemanagement.employeemanagement.utils;

import org.springframework.stereotype.Component;

import com.employeemanagement.employeemanagement.dto.TrainingDTO;
import com.employeemanagement.employeemanagement.entity.Training;

@Component
public class TrainingMapper {
	
	public Training convertToEntity(TrainingDTO dto) {
		Training entity = new Training();
		
		entity.setId(dto.getId());
		entity.setTitle(dto.getTitle());
		entity.setDescription(dto.getDescription());
		
		return entity;
	}
}
