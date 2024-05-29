package com.employeemanagement.employeemanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "TRAINING_EMPLOYEE")
public class TrainingEmployee {
	
	@EmbeddedId
	@JsonIgnore
	private TrainingEmployeeKey id;
	
	@ManyToOne
	@MapsId("idEmployee")
	@JoinColumn(name = "ID_EMPLOYEE")
	@JsonIgnore
	private Employee idEmployee;

	@ManyToOne
	@MapsId("idTraining")
	@JoinColumn(name = "ID_TRAINING")
	private Training idTraining;
	   
	@ManyToOne
	@JoinColumn(name = "ID_STATUS")
	private Status idStatus;

	public TrainingEmployee() {
		
	}
	
	public TrainingEmployee(TrainingEmployeeKey id, Employee idEmployee, Training idTraining, Status idStatus) {
		super();
		this.id = id;
		this.idEmployee = idEmployee;
		this.idTraining = idTraining;
		this.idStatus = idStatus;
	}

	public TrainingEmployeeKey getId() {
		return id;
	}

	public void setId(TrainingEmployeeKey id) {
		this.id = id;
	}

	public Employee getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(Employee idEmployee) {
		this.idEmployee = idEmployee;
	}

	public Training getIdTraining() {
		return idTraining;
	}

	public void setIdTraining(Training idTraining) {
		this.idTraining = idTraining;
	}

	public Status getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(Status idStatus) {
		this.idStatus = idStatus;
	}
	
}
