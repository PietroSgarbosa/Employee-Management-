package com.employeemanagement.employeemanagement.entity;

import java.io.Serializable;
import jakarta.persistence.Embeddable;

@Embeddable
public class EmployeeTrainingId implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long idEmployee;
	
	private Long idTraining;	
	

	public EmployeeTrainingId() {
		}

	public EmployeeTrainingId(Long idEmployee, Long idTraining) {	
		this.idEmployee = idEmployee;
		this.idTraining = idTraining;
	}

	public Long getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(Long idEmployee) {
		this.idEmployee = idEmployee;
	}

	public Long getIdTraining() {
		return idTraining;
	}

	public void setIdTraining(Long idTraining) {
		this.idTraining = idTraining;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
