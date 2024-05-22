package com.employeemanagement.employeemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.employeemanagement.employeemanagement.entity.Training;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {
}
