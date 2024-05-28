package com.employeemanagement.employeemanagement.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.employeemanagement.employeemanagement.dto.EmployeeDTO;
import com.employeemanagement.employeemanagement.dto.EmployeeTrainingDTO;
import com.employeemanagement.employeemanagement.entity.Employee;
import com.employeemanagement.employeemanagement.entity.EmployeeTraining;

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
		
		List<EmployeeTraining> relationshipEntityList = new ArrayList<EmployeeTraining>();
		for (EmployeeTrainingDTO relationshipDTO : dto.getTrainings()) {
			relationshipEntityList.add(employeeTrainingMapper.covertToEntity(relationshipDTO));
		}
		
		entity.setTrainings(relationshipEntityList);
		
		return entity;
	}
	
}
