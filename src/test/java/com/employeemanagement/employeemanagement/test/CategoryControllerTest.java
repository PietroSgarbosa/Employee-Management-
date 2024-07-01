package com.employeemanagement.employeemanagement.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.employeemanagement.employeemanagement.controller.CategoryController;
import com.employeemanagement.employeemanagement.dto.CategoryDTO;
import com.employeemanagement.employeemanagement.entity.Category;
import com.employeemanagement.employeemanagement.service.CategoryService;

@SpringBootTest
public class CategoryControllerTest {
	
	private static final String DATABASE_ERROR = "Database error";
	private static final Long CATEGORY_ID = 1L;
	private static final Long CATEGORY_ID2 = 2l;
	private static final String CATEGORY_DESCRIPTION = "Intern";
	
	@Mock
	@Autowired
	private CategoryService categoryService;
	
	@InjectMocks
	@Autowired
	private CategoryController categoryController;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	void testGetById() {
		//Arrange
		Category categoryEntity = new Category();
		categoryEntity.setId(CATEGORY_ID);
		
		when(categoryService.findById(CATEGORY_ID)).thenReturn(categoryEntity);
		
		//Act
		ResponseEntity<Category> testResponse = (ResponseEntity<Category>) categoryController.getById(CATEGORY_ID);
		
		//Assert
		assertThat(testResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(testResponse.getBody()).hasSameClassAs(categoryEntity);
		assertThat(testResponse.getBody().getId()).isEqualTo(CATEGORY_ID);
		verify(categoryService, times(1)).findById(CATEGORY_ID);
	}
	
	@Test
	void testGetById_WhenThrowsException() {
		//Arrange
		when(categoryService.findById(CATEGORY_ID)).thenThrow(new RuntimeException(DATABASE_ERROR));
		
		//Act
		ResponseEntity<?> testResponse = categoryController.getById(CATEGORY_ID);
		
		//Assert
		assertThat(testResponse.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
		assertThat(testResponse.getBody()).isEqualTo(DATABASE_ERROR);
		verify(categoryService, times(1)).findById(CATEGORY_ID);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	void testGetAll() {
		//Arrange
		CategoryDTO category1 = new CategoryDTO();
		category1.setId(CATEGORY_ID);
		CategoryDTO category2 = new CategoryDTO();
		category2.setId(CATEGORY_ID2);
		
		List<CategoryDTO> listCategory = Arrays.asList(category1, category2);
		
		when(categoryService.getAll()).thenReturn(listCategory);
		
		//Act
		ResponseEntity<List<CategoryDTO>> testResponse = (ResponseEntity<List<CategoryDTO>>) categoryController.getAll();
		
		//Assert
		assertThat(testResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(testResponse.getBody()).hasSameClassAs(listCategory); 
		assertThat(testResponse.getBody().get(0).getId()).isEqualTo(CATEGORY_ID);
		assertThat(testResponse.getBody().get(1).getId()).isEqualTo(CATEGORY_ID2);
		verify(categoryService, times(1)).getAll();
	}
	
	@Test
	void testGetAll_WhenThrowsException() {
		//Arrange
		when(categoryService.getAll()).thenThrow(new RuntimeException(DATABASE_ERROR));
		
		//Act
		ResponseEntity<?> testResponse = categoryController.getAll();
		
		//Assert
		assertThat(testResponse.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
		assertThat(testResponse.getBody()).isEqualTo("Internal Server Error: " + DATABASE_ERROR);
		verify(categoryService, times(1)).getAll();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	void testCreate() {
		//Arrange 
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setDescription(CATEGORY_DESCRIPTION);
		
		Mockito.doNothing().when(categoryService).create(categoryDTO);
		//Act
		ResponseEntity<String> testResponse = (ResponseEntity<String>) categoryController.create(categoryDTO); 

		//Assert
		assertThat(testResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(testResponse.getBody()).isEqualTo("Category inserted successfully!");
		verify(categoryService, times(1)).create(categoryDTO);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	void testCreate_WhenThrowsException() {
		//Arrange 
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setDescription(CATEGORY_DESCRIPTION);
		
		doThrow(new RuntimeException(DATABASE_ERROR)).when(categoryService).create(categoryDTO);
		
		//Act
		ResponseEntity<String> testResponse = (ResponseEntity<String>) categoryController.create(categoryDTO); 
		
		//Assert
		assertThat(testResponse.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
		assertThat(testResponse.getBody()).isEqualTo("Failed trying to insert new data, error message: " + DATABASE_ERROR);
		verify(categoryService, times(1)).create(categoryDTO);
	}
	
	@Test
	void testUpdate() {
		//Arrange 
		String responseMessage = "Category of ID " + CATEGORY_ID + " updated successfully!";
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setDescription(CATEGORY_DESCRIPTION);
		
		when(categoryService.update(categoryDTO)).thenReturn(responseMessage);
		
		//Act
		ResponseEntity<String> testResponse = categoryController.update(categoryDTO);
		
		//Arrange
		assertThat(testResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(testResponse.getBody()).isEqualTo(responseMessage);
		verify(categoryService, times(1)).update(categoryDTO);
	}
	
	@Test
	void testUpdate_WhenThrowsException() {
		//Arrange 
		String responseMessage ="Error trying to update category. Error message: " + DATABASE_ERROR;
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setDescription(CATEGORY_DESCRIPTION);
		
		doThrow(new RuntimeException(DATABASE_ERROR)).when(categoryService).update(categoryDTO);
		
		//Act
		ResponseEntity<String> testResponse = categoryController.update(categoryDTO);
		
		//Arrange
		assertThat(testResponse.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
		assertThat(testResponse.getBody()).isEqualTo(responseMessage);
		verify(categoryService, times(1)).update(categoryDTO);
	}
	
	@Test
	void testDelete() {
		//Arrange
		String responseMessage = "Category deleted sucessfully";
		when(categoryService.delete(CATEGORY_ID)).thenReturn(responseMessage);
		
		//Act
		ResponseEntity<String> testResponse = categoryController.delete(CATEGORY_ID);
		
		//Assert
		assertThat(testResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(testResponse.getBody()).isEqualTo(responseMessage);
		verify(categoryService, times(1)).delete(CATEGORY_ID);
	}
	
	@Test
	void testDelete_WhenThrowsException() {
		//Arrange
		doThrow(new RuntimeException(DATABASE_ERROR)).when(categoryService).delete(CATEGORY_ID);
		
		//Act
		ResponseEntity<String> testResponse = categoryController.delete(CATEGORY_ID);
		
		//Assert
		assertThat(testResponse.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
		assertThat(testResponse.getBody()).isEqualTo("Internal error, message: " + DATABASE_ERROR);
		verify(categoryService, times(1)).delete(CATEGORY_ID);
	}
 
}
