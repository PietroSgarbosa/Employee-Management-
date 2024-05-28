package com.employeemanagement.employeemanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeemanagement.employeemanagement.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	
	
	
	
	private CategoryRepository getCategoryRepository() {
		return categoryRepository;
		
	}
	
	

}
