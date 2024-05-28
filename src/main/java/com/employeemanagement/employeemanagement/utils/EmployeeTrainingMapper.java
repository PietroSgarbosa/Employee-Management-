package com.employeemanagement.employeemanagement.utils;

import org.springframework.stereotype.Component;
import com.employeemanagement.employeemanagement.dto.EmployeeTrainingDTO;
import com.employeemanagement.employeemanagement.entity.EmployeeTraining;

@Component
public class EmployeeTrainingMapper {
	
	private EmployeeMapper employeeMapper;
	
	private TrainingMapper trainingMapper;
	
	public EmployeeTraining covertToEntity(EmployeeTrainingDTO dto) {
		EmployeeTraining entity = new EmployeeTraining();
		entity.setEmployeeId(employeeMapper.covertToEntity(dto.getEmployee()));		
		entity.setStatus(dto.getStatus());
		entity.setTrainingId(trainingMapper.covertToEntity(dto.getTraining()));			
		
		return entity;
	}

}
