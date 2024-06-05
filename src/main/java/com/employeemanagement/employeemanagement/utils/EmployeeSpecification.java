package com.employeemanagement.employeemanagement.utils;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.employeemanagement.employeemanagement.entity.Employee;
import com.employeemanagement.employeemanagement.entity.EmployeeTraining;

import jakarta.persistence.criteria.Join;

public class EmployeeSpecification {

	public static Specification<Employee> firstName(String firstName) {
		return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("firstName"), firstName);
	}

	public static Specification<Employee> middleName(String middleName) {
		return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("middleName"), middleName);
	}

	public static Specification<Employee> lastName(String lastName) {
		return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("lastName"), lastName);
	}

	public static Specification<Employee> cpf(String cpf) {
		return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("cpf"), cpf);
	}

	public static Specification<Employee> employeeWithTraining(List<Long> trainingsId) {
		return (root, query, criteriaBuilder) -> {
			Join<Employee, EmployeeTraining> employeeTrainingJoin = root.join("traninings");
			return employeeTrainingJoin.get("trainings").get("id").in(trainingsId);
		};

	}

}
