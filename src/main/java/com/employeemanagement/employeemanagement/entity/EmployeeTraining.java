package com.employeemanagement.employeemanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@JsonIgnore
	private EmployeeTrainingId id = new EmployeeTrainingId();

	@ManyToOne
	@JoinColumn(name = "status")
	private Status status;
	
	@ManyToOne
	@MapsId("employeeId")
	@JsonIgnore
	@JoinColumn(name = "employee_id")
	private Employee employee;
	
	@ManyToOne
	@MapsId("trainingId")
	@JoinColumn(name = "training_id")
	private Training training;
	
	public EmployeeTraining() {
		super();
	}

	public EmployeeTraining(EmployeeTrainingId id, Status status, Employee employee, Training training) {
		super();
		this.id = id;
		this.status = status;
		this.employee = employee;
		this.training = training;
	}

	public EmployeeTrainingId getId() {
		return id;
	}

	public void setId(EmployeeTrainingId id) {
		this.id = id;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Training getTraining() {
		return training;
	}

	public void setTraining(Training training) {
		this.training = training;
	}
	
	
	
}
