package com.employeemanagement.employeemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.employeemanagement.employeemanagement.dto.CategoryDTO;
import com.employeemanagement.employeemanagement.entity.Category;
import com.employeemanagement.employeemanagement.service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

    @Operation(
    		summary = "Search category by ID", 
    		description = "Returns a category entity by it exactly ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Endpoint working succesfully", content = { @Content(schema = @Schema(implementation = Category.class), mediaType ="application/json")}),
        @ApiResponse(responseCode = "500", description = "Internal error on server/API")
    })
	@GetMapping(value = "/{id}")
	public @ResponseBody ResponseEntity<?> getById(@PathVariable Long id) {
		try {
			Category entity = getCategoryService().findById(id);
			return ResponseEntity.status(HttpStatus.OK).body(entity);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
    
    @Operation(
    		summary = "Submit new category", 
    		description = "Submit new category receiving a CategoryDTO model class")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Endpoint working succesfully", content = @Content( mediaType = "text/plain", schema = @Schema(type = "string", example = "Category inserted succesfully!"))),
        @ApiResponse(responseCode = "500", description = "Internal error on server/API")
    })
	@PostMapping()
	public @ResponseBody ResponseEntity<?> create(@RequestBody CategoryDTO categoryDTO) {
		try {
			getCategoryService().create(categoryDTO);
			return ResponseEntity.status(HttpStatus.OK).body("Category inserted successfully!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed trying to insert new data, error message: " + e.getMessage());
		}
	}

    @Operation(
    		summary = "Get all categories", 
    		description = "Returns a list of CategoryDTO or an empty list")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Endpoint working succesfully", content = { @Content(schema = @Schema(implementation = CategoryDTO.class), mediaType ="application/json")}),
        @ApiResponse(responseCode = "500", description = "Internal error on server/API")
    })
	@GetMapping()
	public @ResponseBody ResponseEntity<?> getAll() {
		try {
			List<CategoryDTO> categoryListDTO = getCategoryService().getAll();
			return ResponseEntity.status(HttpStatus.OK).body(categoryListDTO);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Internal Server Error: " + e.getMessage());
		}
	}

    @Operation(
    		summary = "Delete a category", 
    		description = "Delete a category by sending the correct ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Endpoint working succesfully", content = @Content( mediaType = "text/plain", schema = @Schema(type = "string", example = "Category deleted sucessfully"))),
        @ApiResponse(responseCode = "500", description = "Internal error on server/API")
    })
	@DeleteMapping()
	public @ResponseBody ResponseEntity<String> delete(@RequestParam Long id) {
		try {
			String message = getCategoryService().delete(id);
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Internal error, message: " + e.getMessage());
		}
	}

    @Operation(
    		summary = "Update a category", 
    		description = "Update a category by sending CategoryDTO with changes")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Endpoint working succesfully", content = @Content( mediaType = "text/plain", schema = @Schema(type = "string", example = "Category updated sucessfully"))),
        @ApiResponse(responseCode = "500", description = "Internal error on server/API")
    })
	@PutMapping()
	public @ResponseBody ResponseEntity<String> update(@RequestBody CategoryDTO categoryDTO) {
		try {
			String message = getCategoryService().update(categoryDTO);
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error trying to update category. Error message: " + e.getMessage());
		}
	}

	public CategoryService getCategoryService() {
		return categoryService;
	}
}
