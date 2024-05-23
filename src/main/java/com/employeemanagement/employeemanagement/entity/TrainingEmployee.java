package com.employeemanagement.employeemanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name="EMPLOYEE_TRAINING")
public class TrainingEmployee {
	
	@EmbeddedId
	private TrainingEmployeeId id = new TrainingEmployeeId();
	
	//Muitos para um, pode ter muitos colaboradores porém apenas uma única relação 
	//com o curso
	@MapsId("employeeId")
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "employee_id")
	private Employee employee;
	
	//Muitos cursos para apenas uma relação com um colaborador, para não se repetir
	@MapsId("trainingId")
	@ManyToOne
	@JoinColumn(name = "training_id")
	private Training training;
	
	@ManyToOne
	@JoinColumn(name = "status_ID")
	private Status status;

	public TrainingEmployeeId getId() {
		return id;
	}

	public void setId(TrainingEmployeeId id) {
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
