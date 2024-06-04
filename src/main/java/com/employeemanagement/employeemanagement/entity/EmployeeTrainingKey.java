package com.employeemanagement.employeemanagement.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class EmployeeTrainingKey implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long employee;
	
	private Long training;

	public EmployeeTrainingKey() {
		
	}

	public EmployeeTrainingKey(Long employee, Long training) {
		super();
		this.employee = employee;
		this.training = training;
	}

	public Long getEmployee() {
		return employee;
	}

	public void setEmployee(Long employee) {
		this.employee = employee;
	}

	public Long getTraining() {
		return training;
	}

	public void setTraining(Long training) {
		this.training = training;
	}
	
	
}