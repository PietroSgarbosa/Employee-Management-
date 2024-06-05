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
	
	public static Specification<EmployeeFilterDTO> byCriteria(EmployeeFilterDTO filter){
		return  (root, query, builder) -> {
			List<Predicate> predicates = new ArrayList<>();
			
			if (filter.getFirsName() != null) {
				predicates.add(builder.like(root.get("firstName"), "%" + filter.getFirsName() + "%"));
			}
			
			if (filter.getMiddleName() != null) {
				predicates.add(builder.like(root.get("middleName"), "%" + filter.getMiddleName() + "%"));
			}
			
			if (filter.getLastName() != null) {
				predicates.add(builder.like(root.get("lastName"), "%" + filter.getLastName() + "%"));
			}
			
			if (filter.getCpf() != null) {
				predicates.add(builder.like(root.get("cpf"), "%" + filter.getCpf() + "%"));
			}
			
			if (filter.getTrainingsId() != null && !filter.getTrainingsId().isEmpty()) {
				Join<Employee, EmployeeTraining> trainingJoin = root.join("trainings");
				predicates.add(trainingJoin.get("trainings").in(filter.getTrainingsId()));
				
			}
			return builder.and(predicates.toArray(new Predicate[0]));
			};
	}
}
