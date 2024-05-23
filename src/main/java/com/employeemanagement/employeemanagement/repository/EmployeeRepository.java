package com.employeemanagement.employeemanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.employeemanagement.employeemanagement.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
//	@EntityGraph(value = "Employee.withTrainings", type = EntityGraph.EntityGraphType.LOAD)
//	Optional<Employee> findByIdWithTrainings(@Param("id") Long id);

}
