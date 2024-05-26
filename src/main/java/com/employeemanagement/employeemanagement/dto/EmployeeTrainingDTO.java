package com.employeemanagement.employeemanagement.dto;

public class EmployeeTrainingDTO {

	private EmployeeDTO employee;
	
	private TrainingDTO training;
	
	private StatusDTO status;

	public EmployeeDTO getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeDTO employee) {
		this.employee = employee;
	}

	public TrainingDTO getTraining() {
		return training;
	}

	public void setTraining(TrainingDTO training) {
		this.training = training;
	}

	public StatusDTO getStatus() {
		return status;
	}

	public void setStatus(StatusDTO status) {
		this.status = status;
	}
	
	
}
