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
	
	@JsonIgnore
	@EmbeddedId
	private EmployeeTrainingId id;
	
	@JsonIgnore
	@ManyToOne
	@MapsId("idEmployee")
	@JoinColumn(name = "employee_id")	
	private Employee employee;

	@ManyToOne
	@MapsId("idTraining")
	@JoinColumn(name = "training_id")
	private Training training;
	   
	@ManyToOne
	@JoinColumn(name = "status")
	private Status status = new Status();
	
	public EmployeeTraining () {
		}
	
	public EmployeeTraining(Training training, Employee employee, Status status, EmployeeTrainingId employeeTrainingId) {
		this.training = training;
		this.employee = employee;
		this.status = status;
		this.id = employeeTrainingId;
		
	}	


	public EmployeeTrainingId getId() {
		return id;
	}

	public void setId(EmployeeTrainingId id) {
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
