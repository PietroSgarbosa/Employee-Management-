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
	private EmployeeRepository employeeRepository;

	@Autowired
	private CategoryMapper categoryMapper;

	public Category findById(Long id) {
		return getCategoryRepository().findById(id).orElse(null);
	}

	public List<CategoryDTO> getAll() {
		List<Category> categoryList = getCategoryRepository().findAllByOrderByDescriptionAsc();
		List<CategoryDTO> categoryListDTO = categoryList.stream().map(category -> CategoryDTO.convertToDTO(category))
				.toList();

		if (!categoryListDTO.isEmpty()) {
			return categoryListDTO;
		} else {
			return null;
		}
	}

	public void create(CategoryDTO dto) {
		if (dto != null) {
			Category category = getCategoryMapper().covertToEntity(dto);

			getCategoryRepository().save(category);

		} else {
			throw new IllegalArgumentException("Atributes cannot by null");

		}
	}

	public String update(CategoryDTO categoryDTO) {
		Category defaultCategory = findById(categoryDTO.getId());
		String responseMessage = "Category of ID " + categoryDTO.getId() + " not found";

		if (defaultCategory != null) {

			defaultCategory.setDescription(categoryDTO.getDescription() != null ? categoryDTO.getDescription()
					: defaultCategory.getDescription());
			responseMessage = "Category of ID " + categoryDTO.getId() + " updated successfully!";

			getCategoryRepository().save(defaultCategory);

			return responseMessage;
		}
		return responseMessage;
	}

	public String delete(Long id) {
		Category category = findById(id);
		List<Employee> employees = getEmployeeRepository().findByCategory(category);

		if (employees.isEmpty()) {
			if (category != null) {
				getCategoryRepository().delete(category);
				return "Category deleted sucessfully";
			} else {
				return "Id: " + id + "not found";
			}
		} else {
			return "There are employees registered with the specified category, please check";
		}

	}

	public CategoryRepository getCategoryRepository() {
		return categoryRepository;
	}

	public EmployeeRepository getEmployeeRepository() {
		return employeeRepository;
	}

	public CategoryMapper getCategoryMapper() {
		return categoryMapper;
	}
}
