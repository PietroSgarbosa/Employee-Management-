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
	private EmployeeTrainingKey id;

	@JsonIgnore
	@ManyToOne
	@MapsId("employee")
	@JoinColumn(name = "ID_EMPLOYEE")
	private Employee employee;

	@ManyToOne
	@MapsId("training")
	@JoinColumn(name = "ID_TRAINING")
	private Training training;

	@ManyToOne
	@JoinColumn(name = "ID_STATUS")
	private Status status;
	
	public EmployeeTraining() {
		super();
	}

	public EmployeeTraining(EmployeeTrainingKey id, Employee employee, Training training, Status status) {
		super();
		this.id = id;
		this.employee = employee;
		this.training = training;
		this.status = status;
	}

	public EmployeeTrainingKey getId() {
		return id;
	}

	public void setId(EmployeeTrainingKey id) {
		this.id = id;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}