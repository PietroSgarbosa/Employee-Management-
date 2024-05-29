package com.employeemanagement.employeemanagement.entity;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.NamedSubgraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "EMPLOYEE")
@NamedEntityGraph(name = "Employee.detail",
    attributeNodes = {
    	@NamedAttributeNode("firstName"),
    	@NamedAttributeNode("middleName"),
        @NamedAttributeNode("lastName"),
        @NamedAttributeNode("category"),
        @NamedAttributeNode("cpf"),
        @NamedAttributeNode(value = "trainings", subgraph = "TrainingEmployee.detail")
    },
    subgraphs = {
        @NamedSubgraph(name = "TrainingEmployee.detail",
            attributeNodes = {
                @NamedAttributeNode("idTraining"),
                @NamedAttributeNode("idStatus")
            }
        )
    }
)
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "firstName", nullable = false)
	private String firstName;
	
	@Column(name = "middleName")
	private String middleName;
	
	@Column(name = "lastName", nullable = false)
	private String lastName;
	
	@Column(name = "cpf", nullable = false)
	private String cpf;
	
	@ManyToOne    
	@JoinColumn(name ="ID_CATEGORY")
	private Category category;
	
	@OneToMany(mappedBy = "idEmployee")
	private List<TrainingEmployee> trainings;
	
	public Employee(Long id) {
		this.id = id;
	}
	
	public Employee() {
		
	}
	
	public List<TrainingEmployee> getTrainings() {
		return trainings;
	}

	public void setTrainings(List<TrainingEmployee> trainings) {
		this.trainings = trainings;
	}
	
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
