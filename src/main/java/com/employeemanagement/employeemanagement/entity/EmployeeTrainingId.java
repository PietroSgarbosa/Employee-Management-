package com.employeemanagement.employeemanagement.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Embeddable;

@Embeddable
public class EmployeeTrainingId implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long employeeId;
	
	private Long trainingId;


	public EmployeeTrainingId() {
		super();
	}	
	
	public EmployeeTrainingId(Long employeeId, Long trainingId) {		
		this.employeeId = employeeId;
		this.trainingId = trainingId;
	}
	
	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	public Long getTrainingId() {
		return trainingId;
	}
	public void setTrainingId(Long trainingId) {
		this.trainingId = trainingId;
	}		
}
