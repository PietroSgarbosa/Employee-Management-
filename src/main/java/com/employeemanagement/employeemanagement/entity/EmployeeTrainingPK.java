package com.employeemanagement.employeemanagement.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class EmployeeTrainingPK implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long employeeId;

    private Long trainingId;

    // Getters and Setters


	public EmployeeTrainingPK(Long id, Long trainingId) {
		super();
		this.employeeId = id;
		this.trainingId = trainingId;
	}
	
	public EmployeeTrainingPK() {
		super();

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
