package com.employeemanagement.employeemanagement.utils;

import org.springframework.stereotype.Component;

import com.employeemanagement.employeemanagement.dto.TrainingDTO;
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
	
	private static TrainingDTO mapToTrainingDTO(TrainingDTO training) {
		TrainingDTO dto = new TrainingDTO();
		dto.setId(training.getId());
		dto.setTitle(training.getTitle());
		dto.setStatus(training.getStatus());
		return dto;
	}
}