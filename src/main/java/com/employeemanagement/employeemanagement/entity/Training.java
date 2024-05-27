package com.employeemanagement.employeemanagement.entity;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "TRAINING")
public class Training {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "NAME")
	private String title;
	    
	@Column(name = "DESCRIPTION")
	private String description;	
	
	@Column(name = "DURATION")
	private String duration;
	
	@OneToMany(mappedBy = "trainingId")
	private List<EmployeeTraining> employee;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}	
	
	public List<EmployeeTraining> getEmployee() {
		return employee;
	}

	public void setEmployee(List<EmployeeTraining> employee) {
		this.employee = employee;
	}
	
}
