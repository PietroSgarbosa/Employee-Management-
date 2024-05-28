package com.employeemanagement.employeemanagement.entity;

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
	
	@ManyToOne
	@JoinColumn(name = "status_id")
	private Status status;	
	
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

	public Status getStatus() {
		return getStatus();
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}

}
