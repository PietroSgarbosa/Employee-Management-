package com.employeemanagement.employeemanagement.specification;

import org.springframework.data.jpa.domain.Specification;

import com.employeemanagement.employeemanagement.entity.Employee;

public class EmployeeSpecification {
	
	public static Specification<Employee> firstName(String firstName) {
	    return (root, criteriaQuery, criteriaBuilder) ->
	            criteriaBuilder.equal(root.get("firstName"), firstName);
	}

	public static Specification<Employee> lastName(String lastName) {
	    return (root, criteriaQuery, criteriaBuilder) ->
	            criteriaBuilder.equal(root.get("lastName"), lastName);
	}

	public static Specification<Employee> cpf(String cpf) {
	    return (root, criteriaQuery, criteriaBuilder) ->
	            criteriaBuilder.equal(root.get("cpf"), cpf);
	}

}
