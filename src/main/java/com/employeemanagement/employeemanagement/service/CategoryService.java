package com.employeemanagement.employeemanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeemanagement.employeemanagement.dto.CategoryDTO;
import com.employeemanagement.employeemanagement.entity.Category;
import com.employeemanagement.employeemanagement.entity.Employee;
import com.employeemanagement.employeemanagement.repository.CategoryRepository;
import com.employeemanagement.employeemanagement.repository.EmployeeRepository;
import com.employeemanagement.employeemanagement.utils.CategoryMapper;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CategoryMapper categoryMapper;

	@Autowired
	private EmployeeRepository employeeRepository;
	
	public Category finById(Long id) {
		return getCategoryRepository().findById(id).orElse(null);
	}


	public List<CategoryDTO> getAll() {
		List<Category> categoryList = getCategoryRepository().findAll();
		List<CategoryDTO> categoryListDTO = categoryList.stream().map(category -> CategoryDTO.convertToDTO(category)).toList();
		return categoryListDTO;
	}

	public void create(CategoryDTO dto) {
			Category category = getCategoryMapper().covertToEntity(dto);
			getCategoryRepository().save(category);
	}

	public void update(CategoryDTO categoryDTO) {
		Category defaultCategory = getCategoryMapper().covertToEntity(categoryDTO);
		getCategoryRepository().save(defaultCategory);
	}

	public String delete(Long id) {
		Category category = finById(id);
		String message = "Category removed Sucessfully !";
		List<Employee> employees = getEmployeeRepository().findByCategory(category);
		if(!employees.isEmpty()) {
			message = "It was not possible to remove this category, there are still registered employees, Change the Category of these Employess before removing this category :";
			for(Employee employee: employees) {
				 message += employee.getFirstName() + " " + employee.getLastName() + " " + "\n";
			}
		}else {
			getCategoryRepository().deleteById(id);
		}
		return message;
	}

	private EmployeeRepository getEmployeeRepository() {
		return employeeRepository;
	}
	
	private CategoryRepository getCategoryRepository() {
		return categoryRepository;
	}

	private CategoryMapper getCategoryMapper() {
		return categoryMapper;
	}
}
