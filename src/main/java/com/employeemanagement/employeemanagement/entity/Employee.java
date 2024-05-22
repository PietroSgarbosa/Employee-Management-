	package com.employeemanagement.employeemanagement.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.NamedSubgraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@NamedEntityGraph(
		name = "Employee.withTrainings",
		attributeNodes = {
			@NamedAttributeNode(value = "trainings", subgraph = "trainings-subgraph")
		},
		subgraphs = {
			@NamedSubgraph(
				name = "trainings-subgraph",
				attributeNodes = {
					@NamedAttributeNode("training")
				}
			)
		}
	)
@Table(name = "EMPLOYEE")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "firstName")
	private String firstName;
	
	@Column(name = "middleName")
	private String middleName;
	
	@Column(name = "lastName")
	private String lastName;
	
	@Column(name = "category")
	private String category;
	
	@Column(name = "cpf")
	private String cpf;
	
	@OneToMany(
			mappedBy = "employee",
			fetch = FetchType.EAGER
			)
	private List<TrainingEmployee> trainings;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public List<TrainingEmployee> getTrainings() {
		return trainings;
	}

	public void setTrainings(List<TrainingEmployee> trainings) {
		this.trainings = trainings;
	}
	
}
