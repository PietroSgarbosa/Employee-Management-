package com.employeemanagement.employeemanagement.utils;

import org.springframework.data.jpa.domain.Specification;

import com.employeemanagement.employeemanagement.dto.EmployeeFilterDTO;
import com.employeemanagement.employeemanagement.entity.Employee;
import com.employeemanagement.employeemanagement.entity.EmployeeTraining;

import jakarta.persistence.criteria.Join;

public class EmployeeSpecification {

	public static Specification<Employee> employeeFilter(EmployeeFilterDTO dto) {

		if (dto.getFirstName() != null) {
			return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("firstName"),
					dto.getFirstName());
		}

		if (dto.getMiddleName() != null) {
			return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("middleName"),
					dto.getMiddleName());
		}

		if (dto.getLastName() != null) {
			return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("lastName"),
					dto.getLastName());
		}

		if (dto.getCpf() != null) {
			return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("cpf"), dto.getCpf());
		}

		if (dto.getTraining() != null) {
			return (root, query, criteriaBuilder) -> {
				Join<Employee, EmployeeTraining> employeeTrainingJoin = root.join("trainings");
				return employeeTrainingJoin.get("training").get("id").in(dto.getTraining());
			};
		}
		return null;

	}
}
