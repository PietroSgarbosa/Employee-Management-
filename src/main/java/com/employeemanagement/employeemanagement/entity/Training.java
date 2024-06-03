package com.employeemanagement.employeemanagement.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

	@Column(name = "title", nullable = false, unique = true)
	private String title;

	@Column(name = "description", nullable = false, unique = true)
	private String description;

	@OneToMany(mappedBy = "training")
	@JsonIgnore
	private Set<EmployeeTraining> employees = new HashSet<>();

	public Training(Long id) {
		super();
		this.id = id;
	}

	public Training() {
		super();
	}

	public Long getID() {
		return id;
	}

	public void setID(Long id) {
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

	public Set<EmployeeTraining> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<EmployeeTraining> employees) {
		this.employees = employees;
	}

}
