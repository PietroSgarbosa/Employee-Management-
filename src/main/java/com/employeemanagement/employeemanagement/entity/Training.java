package com.employeemanagement.employeemanagement.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "TRAININGS")
public class Training {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "TITLE", nullable = false, unique = true)
	private String title;
	    
	@Column(name = "DESCRIPTION", nullable = false, unique = true)
	private String description;

	@OneToMany(mappedBy = "idTraining")
	@JsonIgnore
	private List<TrainingEmployee> employees;
	
	public Training(Long id) {
		this.id = id;
	}
	
	public Training() {
		
	}

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

	public List<TrainingEmployee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<TrainingEmployee> employees) {
		this.employees = employees;
	}

}
