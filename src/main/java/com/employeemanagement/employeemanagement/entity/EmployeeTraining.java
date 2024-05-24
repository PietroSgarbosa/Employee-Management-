package com.employeemanagement.employeemanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "EMPLOYEE_TRAINING")
public class EmployeeTraining {
	
	@EmbeddedId
	private EmployeeTrainingId id;	
	
	@ManyToOne	
	@MapsId("employeeId")
	@JoinColumn(name = "employee_id")
	private Employee employeeId;
	
	@ManyToOne	
	@MapsId("trainingId")
	@JoinColumn(name = "training_id")
	private Training trainingId;	
	
	@Column(name = "status")
	private String status;

//	public EmployeeTraining(Employee employee, Training training) {		
//		this.employeeId = employee;
//		this.trainingId = training;
//	}

	public EmployeeTrainingId getId() {
		return id;
	}

	public void setId(EmployeeTrainingId id) {
		this.id = id;
	}

	public Employee getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Employee employeeId) {
		this.employeeId = employeeId;
	}

	public Training getTrainingId() {
		return trainingId;
	}

	public void setTrainingId(Training trainingId) {
		this.trainingId = trainingId;
	}

	public String getStatus() {
		return getStatus();
	}

	public void setStatus(String status) {
		this.status = status;
	}		
	
}
