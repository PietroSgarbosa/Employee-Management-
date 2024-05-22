package com.employeemanagement.employeemanagement.entity;

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
	private TrainingEmployeeKey id;
	
	@ManyToOne
	@MapsId("idEmployee")
	@JoinColumn(name = "ID_EMPLOYEE")
	private Employee idEmployee;

	@ManyToOne
	@MapsId("idTraining")
	@JoinColumn(name = "ID_TRAINING")
	private Training idTraining;
	   
	@ManyToOne
	@JoinColumn(name = "ID_STATUS")
	private Status idStatus;

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
