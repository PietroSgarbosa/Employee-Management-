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
	
	public Category getById(Long id) {
		return getCategoryRepository().findById(id).orElse(null);
	}

	public List<CategoryDTO> getAll(){
		List<Category> trainingList = getCategoryRepository().findAll();
		List<CategoryDTO> trainingListDTO = trainingList.stream().map(training -> CategoryDTO.convertToDTO(training)).toList();
		
		if(!trainingListDTO.isEmpty()) {
			return trainingListDTO;
		} else {
			return null;
		}
	}
	
	public String update(CategoryDTO categoryDTO) {
		Category defaultCategory = getById(categoryDTO.getId());
		String responseMessage = "Collaborator of ID " + categoryDTO.getId() + " not found";

		if(defaultCategory != null) {			
			defaultCategory.setDescription(categoryDTO.getDescription());
			create(CategoryDTO.convertToDTO(defaultCategory));
			
			responseMessage = "Category of ID " + categoryDTO.getId() + " updated successfully!";

			return responseMessage;
		}
		return responseMessage;
	}
	
	public void create(CategoryDTO categoryDTO) {
		if(categoryDTO != null) {
			if(categoryDTO.getDescription() == null) {
				throw new IllegalArgumentException("The name and description cannot be null");
			} else {
				Category categoryEntity = getCategoryMapper().covertToEntity(categoryDTO);
				getCategoryRepository().save(categoryEntity);
			}
		} else {
			throw new IllegalArgumentException("The trainingDTO object cannot be null");
		}
	}
	
	public String delete(Long id) {
		Category category = getById(id);
		
		if(category == null) {
			return "This category ID " + id + " doesn't exist";
		} else {
			getCategoryRepository().deleteById(id);
			return "ategory of ID " + id + " removed!";
		}
	}
	
	private CategoryRepository getCategoryRepository() {
		return categoryRepository;		
	}	
	

	private CategoryMapper getCategoryMapper() {
		return categoryMapper;
	}
}
