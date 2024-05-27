package com.employeemanagement.employeemanagement.dto;


import ch.qos.logback.core.status.Status;

public class EmployeeTrainingDTO {	
	
	private EmployeeDTO employee;
	private TrainingDTO training;
	private Status status;
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
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
}
