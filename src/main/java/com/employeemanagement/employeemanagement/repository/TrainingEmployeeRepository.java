package com.employeemanagement.employeemanagement.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.employeemanagement.employeemanagement.entity.TrainingEmployee;
import com.employeemanagement.employeemanagement.entity.TrainingEmployeeKey;

@Repository
public interface TrainingEmployeeRepository extends JpaRepository<TrainingEmployee, TrainingEmployeeKey>{

	@Query(
			value = "SELECT * FROM TRAINING_EMPLOYEE WHERE ID_EMPLOYEE = :id",
			nativeQuery = true
	)
	public List<TrainingEmployee> getByNativeQuery(@Param("id") Long id);
}
