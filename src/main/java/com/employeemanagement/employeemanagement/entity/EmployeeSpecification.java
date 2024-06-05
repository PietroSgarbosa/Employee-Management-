package com.employeemanagement.employeemanagement.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;


import com.employeemanagement.employeemanagement.dto.EmployeeFilterDTO;

import jakarta.persistence.criteria.Join;

import jakarta.persistence.criteria.Predicate;

public class EmployeeSpecification {

    public static Specification<Employee> withAttributes(EmployeeFilterDTO employeeFilter) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            
            if (employeeFilter.getId() != null) {
                predicates.add(builder.equal(root.get("id"), employeeFilter.getId()));
            }
            
            if (employeeFilter.getFirstName() != null) {
                predicates.add(builder.equal(root.get("firstName"), employeeFilter.getFirstName()));
            }
            
            if (employeeFilter.getMiddleName() != null) {
                predicates.add(builder.equal(root.get("middleName"), employeeFilter.getMiddleName()));
            }
            
            if (employeeFilter.getLastName() != null) {
                predicates.add(builder.equal(root.get("lastName"), employeeFilter.getLastName()));
            }
            
            if (employeeFilter.getCpf() != null) {
                predicates.add(builder.equal(root.get("cpf"), employeeFilter.getCpf()));
            }
            
            if (employeeFilter.getTrainingId() != null) {
                Join<Object, Object> trainingJoin = root.join("trainings");
                predicates.add(builder.equal(trainingJoin.get("training").get("id"), employeeFilter.getTrainingId()));
            }
            
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
