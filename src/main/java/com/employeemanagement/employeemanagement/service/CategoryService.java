package com.employeemanagement.employeemanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeemanagement.employeemanagement.dto.CategoryDTO;
import com.employeemanagement.employeemanagement.entity.Category;
import com.employeemanagement.employeemanagement.repository.CategoryRepository;
import com.employeemanagement.employeemanagement.utils.CategoryMapper;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CategoryMapper categoryMapper;

	public Category finById(Long id) {
		return getCategoryRepository().findById(id).orElse(null);
	}


	public List<CategoryDTO> getAll() {
		List<Category> categoryList = getCategoryRepository().findAll();
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
		Category defaultCategory = finById(categoryDTO.getId());
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
		Category category = finById(id);

		if (category == null) {
			return "This category ID " + id + " doesn't exist";
		} else {
			getCategoryRepository().deleteById(id);
			return "Category of ID " + id + " removed!";
		}
	}

	private CategoryRepository getCategoryRepository() {
		return categoryRepository;
	}

	private CategoryMapper getCategoryMapper() {
		return categoryMapper;
	}
}
