package com.employeemanagement.employeemanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.employeemanagement.employeemanagement.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@EntityGraph(value = "Employee.detail", type = EntityGraph.EntityGraphType.LOAD)
	Optional<Employee> findById(Long id);
	
	List<Employee> findAll(Specification<Employee> specification);
}
