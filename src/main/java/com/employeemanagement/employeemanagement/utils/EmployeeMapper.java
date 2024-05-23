package com.employeemanagement.employeemanagement.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.employeemanagement.employeemanagement.dto.EmployeeDTO;
import com.employeemanagement.employeemanagement.dto.TrainingEmployeeDTO;
import com.employeemanagement.employeemanagement.entity.Employee;
import com.employeemanagement.employeemanagement.entity.TrainingEmployee;

@Component
public class EmployeeMapper {
	
	private EmployeeTrainingMapper employeeTrainingMapper;

	public Employee covertToEntity(EmployeeDTO dto) {
		Employee entity = new Employee();
		entity.setId(dto.getId());
		entity.setFirstName(dto.getFirstName());
		entity.setMiddleName(dto.getMiddleName());
		entity.setLastName(dto.getLastName());
		entity.setCpf(dto.getCpf());
		entity.setCategory(dto.getCategory());
		
		List<TrainingEmployee> relationshipEntityList = new ArrayList<TrainingEmployee>();
		for (TrainingEmployeeDTO relationshipDTO : dto.getTrainings()) {
			relationshipEntityList.add(employeeTrainingMapper.covertToEntity(relationshipDTO));
		}	
		
		entity.setTrainings(relationshipEntityList);
		
		return entity;
	}
	
}
