package com.employeemanagement.employeemanagement.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.employeemanagement.employeemanagement.dto.CategoryDTO;
import com.employeemanagement.employeemanagement.entity.Category;
import com.employeemanagement.employeemanagement.entity.Employee;
import com.employeemanagement.employeemanagement.repository.CategoryRepository;
import com.employeemanagement.employeemanagement.repository.EmployeeRepository;
import com.employeemanagement.employeemanagement.service.CategoryService;

//É necessário importar esses métodos estáticos
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CategoryServiceTest {
	
	@Mock
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Mock
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@InjectMocks
	@Autowired
	private CategoryService categoryService;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test 
	void testGetAll() {
		//Arrange
		Category category1 = new Category();
		category1.setId(1L);
		category1.setDescription("Intern");
		
		Category category2 = new Category();
		category2.setId(2L);
		category2.setDescription("Trainee");
		
		Category category3 = new Category();
		category3.setId(3L);
		category3.setDescription("Jr I");
		
		List<Category> categoryList = Arrays.asList(category1, category2, category3);
		
		when(categoryRepository.findAll()).thenReturn(categoryList);
		
		//"Act"
		List<CategoryDTO> result = categoryService.getAll();
		
		//"Assert"
		assertThat(result).hasSize(3);
		assertThat(result.get(0).getDescription()).isEqualTo("Intern");
		assertThat(result.get(1).getDescription()).isEqualTo("Trainee");
		assertThat(result.get(2).getDescription()).isEqualTo("Jr I");
		verify(categoryRepository, times(1)).findAll(); 
	}
	
	@Test
	void testGetAll_WhenServiceReturnsNull() {
		
		//"Arrange"
		when(categoryService.getAll()).thenReturn(Collections.emptyList());
		
		//"Act"
		List<CategoryDTO> result = categoryService.getAll();
		
		//"Assert"
		assertThat(result).isNull();
		verify(categoryRepository, times(1)).findAll();
	}
	
	@Test
	void testGetById() {
		//Arrange
		Optional<Category> category1 = Optional.ofNullable(new Category());
		category1.get().setId(1L);
		category1.get().setDescription("Intern");
		
		Employee employee1 = new Employee();
		employee1.setId(1L);
		employee1.setCpf("111");
		employee1.setFirstName("Pietro Sgarbosa");
		
		List<Employee> employeeList1 = Arrays.asList(employee1);
		Long id = category1.get().getId();
		
		category1.get().setEmployees(employeeList1);
		
		when(categoryRepository.findById(id)).thenReturn(category1);
		
		//"Act"
		Optional<Category> result = Optional.ofNullable(categoryService.findById(id));
		
		//"Assert"
		assertThat(result).isNotNull();
		assertThat(result.get().getDescription()).isEqualTo("Intern");
		assertThat(result.get().getId()).isEqualTo(1L);
		assertThat(result.get().getEmployees().get(0).getFirstName()).isEqualTo("Pietro Sgarbosa");
		verify(categoryRepository, times(1)).findById(id);
	}
	
	@Test
	void testGetById_WhenReturnIsDifferent() {
		
		Optional<Category> category1 = Optional.ofNullable(new Category());
		category1.get().setId(1L);
		category1.get().setDescription("Intern");

		Long id = 2L;
		
		when(categoryRepository.findById(id)).thenReturn(category1);
		
		//"Act"
		Optional<Category> result = Optional.ofNullable(categoryService.findById(id));
		
		//"Assert"
		assertThat(result.get().getId()).isNotEqualTo(id);
		verify(categoryRepository, times(1)).findById(id);
	}
	
	@Test
	void testCreate() {
		//Arrange
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setId(1L);
		categoryDTO.setDescription("Intern");
		
		Category category1 = new Category();
		category1.setId(1L);
		category1.setDescription("Intern");
		
		//"Act"
		categoryService.create(categoryDTO);
	
		//Assert
		verify(categoryRepository, times(1)).save(refEq(category1));
	}
	
	@Test
	void testCreate_WhenServiceThrowsException() {
	
		//"Act & Assert"
		assertThrows(IllegalArgumentException.class, () -> {
			categoryService.create(null);
		});
	}
	
	@Test
	void testUpdate() {
		//Assert
		Long id = 1L;
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setId(id);
		categoryDTO.setDescription("Trainee");
		
		Category categoryToBeUpdated = new Category();
		categoryToBeUpdated.setId(id);
		categoryToBeUpdated.setDescription("Intern");
		
		when(categoryRepository.findById(id)).thenReturn(Optional.of(categoryToBeUpdated));
		
		//"Act"
		String result = categoryService.update(categoryDTO);

		//"Assert" 
		verify(categoryRepository, times(1)).findById(id);
		verify(categoryRepository, times(1)).save(categoryToBeUpdated);
		assertThat(result).isEqualTo("Category of ID " + id + " updated successfully!");
		assertThat(categoryToBeUpdated.getDescription()).isEqualTo("Trainee");
	}
	
	@Test
	void testUpdate_SecondReturnCondition() {
		//Assert
		Long categoryId = 999L;
		
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setId(categoryId);
		
		Category category = new Category();
		
		when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));

		//"Act"
		String result = categoryService.update(categoryDTO);
		
		assertThat(result).isEqualTo("Category of ID " + categoryDTO.getId() + " not found");
		verify(categoryRepository, times(0)).save(any(Category.class)); 
	}
	
	@Test
	void testDelete() {
		//Arrange
		Long id = 1L;
		
		Category category = new Category();
		category.setId(id);
		
		List<Employee> employees = new ArrayList<Employee>();
		
		when(categoryRepository.findById(id)).thenReturn(Optional.of(category));
		when(employeeRepository.findByCategory(category)).thenReturn(employees);
		
		//"Act" 
		categoryService.delete(id);
		
		//"Assert" 
		verify(categoryRepository, times(1)).findById(id);
		verify(employeeRepository, times(1)).findByCategory(category);
		verify(categoryRepository, times(1)).delete(category);
	}

}
