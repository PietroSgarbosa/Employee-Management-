package com.employeemanagement.employeemanagement.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.employeemanagement.employeemanagement.dto.EmployeeDTO;
import com.employeemanagement.employeemanagement.dto.TrainingDTO;
import com.employeemanagement.employeemanagement.dto.TrainingEmployeeDTO;
import com.employeemanagement.employeemanagement.entity.Employee;
import com.employeemanagement.employeemanagement.entity.Training;
import com.employeemanagement.employeemanagement.entity.TrainingEmployee;

@Component
public class TrainingMapper {

	private EmployeeTrainingMapper employeeTrainingMapper;
	
	public Training covertToEntity(TrainingDTO dto) {
		Training entity = new Training();
		entity.setId(dto.getId());
		entity.setTitle(dto.getTitle());
		entity.setDescription(dto.getDescription());
		
		List<TrainingEmployee> relationshipEntityList = new ArrayList<TrainingEmployee>();
		for (TrainingEmployeeDTO relationshipDTO : dto.getEmployees()) {
			relationshipEntityList.add(employeeTrainingMapper.covertToEntity(relationshipDTO));
		}	
		
		entity.setEmployees(relationshipEntityList);
		
		return entity;
	}
}
