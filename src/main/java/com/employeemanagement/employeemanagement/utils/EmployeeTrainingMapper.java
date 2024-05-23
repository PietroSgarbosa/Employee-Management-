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
public class EmployeeTrainingMapper {
	
	private EmployeeMapper employeeMapper;
	
	private TrainingMapper trainingMapper;
	
	public TrainingEmployee covertToEntity(TrainingEmployeeDTO dto) {
		TrainingEmployee entity = new TrainingEmployee();
		entity.setEmployee(employeeMapper.covertToEntity(dto.getEmployee()));
		entity.setStatus(dto.getStatus());
		entity.setTraining(trainingMapper.covertToEntity(dto.getTraining()));

		return entity;
	}

}
