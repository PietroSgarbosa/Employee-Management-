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
public class EmployeeTraining  {

    @EmbeddedId
    @JsonIgnore
    private EmployeeTrainingPK id = new EmployeeTrainingPK();

    @ManyToOne
    @JsonIgnore
    @MapsId("employeeId")
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @MapsId("trainingId")
    @JoinColumn(name = "training_id")
    private Training training;

	public EmployeeTrainingPK getId() {
		return id;
	}

	public void setId(EmployeeTrainingPK id) {
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
}
