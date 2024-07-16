package com.employeemanagement.employeemanagement.dto;

import java.util.List;

import com.employeemanagement.employeemanagement.utils.Views;
import com.fasterxml.jackson.annotation.JsonView;

public class QualificationDTO {
	
	@JsonView(Views.Basic.class)
	private EmployeeDTO employee;
	
	@JsonView(Views.Basic.class)
	private List<EmployeeTrainingDTO> trainings;

	public List<EmployeeTrainingDTO> getTrainings() {
		return trainings;
	}

	public void setTrainings(List<EmployeeTrainingDTO> trainings) {
		this.trainings = trainings;
	}

	public EmployeeDTO getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeDTO employee) {
		this.employee = employee;
	}

}
