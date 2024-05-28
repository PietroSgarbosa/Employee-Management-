package com.employeemanagement.employeemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeemanagement.employeemanagement.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
