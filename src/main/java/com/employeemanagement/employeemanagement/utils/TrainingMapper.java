package com.employeemanagement.employeemanagement.utils;

import org.springframework.stereotype.Component;

import com.employeemanagement.employeemanagement.dto.TrainingDTO;
import com.employeemanagement.employeemanagement.entity.Training;

@Component
public class TrainingMapper {

	
	public Training covertToEntity(TrainingDTO dto) {
		Training entity = new Training();
		entity.setId(dto.getID());
		entity.setTitle(dto.getTitle());
		entity.setDescription(dto.getDescription());
		entity.setDuration(dto.getDuration());
		
		return entity;
	}
}
