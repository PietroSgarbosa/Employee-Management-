package com.employeemanagement.employeemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.employeemanagement.employeemanagement.dto.CategoryDTO;
import com.employeemanagement.employeemanagement.entity.Category;
import com.employeemanagement.employeemanagement.service.CategoryService;

@Controller
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping(value = "/getById/{id}")
	public @ResponseBody ResponseEntity<?> getById(@PathVariable Long id) {
		try {
			Category entity = getCategoryService().finById(id);
			return ResponseEntity.status(HttpStatus.OK).body(entity);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = "/create")
	public @ResponseBody ResponseEntity<?> create(@RequestBody CategoryDTO categoryDTO) {
		try {
			getCategoryService().create(categoryDTO);
			return ResponseEntity.status(HttpStatus.OK).body("Category inserted successfully!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed trying to insert new data, error message: " + e.getMessage());
		}
	}

	@GetMapping(value = "/getAll")
	public @ResponseBody ResponseEntity<?> getAll() {
		try {
			List<CategoryDTO> categoryListDTO = getCategoryService().getAll();
			return ResponseEntity.status(HttpStatus.OK).body(categoryListDTO);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Internal Server Error: " + e.getMessage());
		}
	}

	@DeleteMapping("/delete")
	public @ResponseBody ResponseEntity<String> delete(@RequestParam Long id) {
		try {
			getCategoryService().delete(id);
			return ResponseEntity.status(HttpStatus.OK).body("Category deleted succesfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Internal error, message: " + e.getMessage());
		}
	}

	@PutMapping(value = "/update")
	public @ResponseBody ResponseEntity<String> update(@RequestBody CategoryDTO categoryDTO) {
		try {
			getCategoryService().update(categoryDTO);
			return ResponseEntity.status(HttpStatus.OK).body("Category updated Sucesfully.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error trying to update category. Error message: " + e.getMessage());
		}
	}

	private CategoryService getCategoryService() {
		return categoryService;
	}
}
