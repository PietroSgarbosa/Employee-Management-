package com.employeemanagement.employeemanagement.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.employeemanagement.employeemanagement.dto.EmployeeFilterDTO;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;

@Component
public class EmployeeSpecification {
	
	public static Specification<Employee> byCriteria(EmployeeFilterDTO employeeFilterDTO){
		return  (root, query, builder) -> {
			List<Predicate> predicates = new ArrayList<>();
			
			if (employeeFilterDTO.getFirstName() != null) {
				predicates.add(builder.like(root.get("firstName"), "%" + employeeFilterDTO.getFirstName() + "%"));
				
			}
			
			if (employeeFilterDTO.getMiddleName() != null) {
				predicates.add(builder.like(root.get("middleName"), "%" + employeeFilterDTO.getMiddleName() + "%"));
			}
			
			if (employeeFilterDTO.getLastName() != null) {
				predicates.add(builder.like(root.get("lastName"), "%" + employeeFilterDTO.getLastName() + "%"));
			}
			
			if (employeeFilterDTO.getCpf() != null) {
				predicates.add(builder.like(root.get("cpf"), "%" + employeeFilterDTO.getCpf() + "%"));
			}
			
			if (employeeFilterDTO.getTrainingsId() != null) {
				
				Join<Object, Object> trainingJoin = root.join("trainings");
				
				predicates.add(builder.equal(trainingJoin.get("training").get("id"), employeeFilterDTO.getTrainingsId()));
				
				
			}
			return builder.and(predicates.toArray(new Predicate[0]));
			};
	}
}
