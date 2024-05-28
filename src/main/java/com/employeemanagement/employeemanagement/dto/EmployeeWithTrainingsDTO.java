package com.employeemanagement.employeemanagement.dto;

import java.util.List;

import com.employeemanagement.employeemanagement.entity.Category;
import com.employeemanagement.employeemanagement.entity.Training;

public class EmployeeWithTrainingsDTO {
	
	private String firstName;
	
	private String middleNAme;
	
	private String lastName;
	
	private String cpf;
	
	private Category category;
	
	private List<Training> trainings;	

}
