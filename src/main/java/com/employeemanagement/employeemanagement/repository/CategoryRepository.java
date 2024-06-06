package com.employeemanagement.employeemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employeemanagement.employeemanagement.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
