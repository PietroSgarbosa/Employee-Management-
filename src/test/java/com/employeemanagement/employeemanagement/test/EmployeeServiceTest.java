package com.employeemanagement.employeemanagement.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockedStatic.Verification;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;

import com.employeemanagement.employeemanagement.dto.EmployeeDTO;
import com.employeemanagement.employeemanagement.dto.EmployeeFilterDTO;
import com.employeemanagement.employeemanagement.entity.Category;
import com.employeemanagement.employeemanagement.entity.Employee;
import com.employeemanagement.employeemanagement.exception.EmployeeDTOMissingException;
import com.employeemanagement.employeemanagement.exception.EmployeeNameMissingException;
import com.employeemanagement.employeemanagement.repository.CategoryRepository;
import com.employeemanagement.employeemanagement.repository.EmployeeRepository;
import com.employeemanagement.employeemanagement.repository.TrainingRepository;
import com.employeemanagement.employeemanagement.service.EmployeeService;
import com.employeemanagement.employeemanagement.utils.EmployeeMapper;
import com.employeemanagement.employeemanagement.utils.EmployeeSpecification;

@SpringBootTest
public class EmployeeServiceTest {

	private static final String LN_2 = "LN_2";
	private static final String FN_2 = "FN_2";
	private static final String LN_1 = "LN_1";
	private static final String FN_1 = "FN_1";
	private static final Long EMPLOYEE_ID = 1L;
	private static final String EMPLOYEE_CPF = "123";
	private static final Long EMPLOYEE_ID2 = 2L;
	private static final String EMPLOYEE_CPF2 = "321";
	
	@Mock
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Mock
	@Autowired 
	private CategoryRepository categoryRepository;
	
	@Mock
	@Autowired
	private TrainingRepository trainingRepository;
	
	@Autowired
	@InjectMocks
	private EmployeeService employeeService;
	
	@Spy
	EmployeeService mockedSpy = new EmployeeService();
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void testGetById() {
		//Arrange
		Optional<Employee> employeeEntity = Optional.ofNullable(new Employee());
		employeeEntity.get().setId(EMPLOYEE_ID);
		employeeEntity.get().setCpf(EMPLOYEE_CPF);
		when(employeeRepository.findById(EMPLOYEE_ID)).thenReturn(employeeEntity);
		
		//Act
		EmployeeDTO result = employeeService.getById(EMPLOYEE_ID);
		
		//Assert
		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo(EMPLOYEE_ID);
		assertThat(result.getCpf()).isEqualTo(EMPLOYEE_CPF);
		verify(employeeRepository, times(1)).findById(EMPLOYEE_ID);
	}
	
	@Test
	void testGetById_WhenReturnIsNull() {
		//Arrange
		Long inexistentId = 200L;
		when(employeeRepository.findById(inexistentId)).thenReturn(null);
		
		//Act
		EmployeeDTO result = employeeService.getById(EMPLOYEE_ID);
		
		//Assert
		assertThat(result).isNull(); 
		verify(employeeRepository, times(1)).findById(EMPLOYEE_ID);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	void testGetAll() {
		//Arrange
		EmployeeFilterDTO filter = new EmployeeFilterDTO();
		Specification<Employee> specification = mock(Specification.class);
		MockedStatic<EmployeeSpecification> mockedStaticClass = mockStatic(EmployeeSpecification.class);
		
		Employee employee1 = new Employee();
		employee1.setId(EMPLOYEE_ID);
		employee1.setCpf(EMPLOYEE_CPF);
		employee1.setFirstName(FN_1);
		employee1.setLastName(LN_1);
		
		Employee employee2 = new Employee();
		employee2.setId(EMPLOYEE_ID2);
		employee2.setCpf(EMPLOYEE_CPF2);
		employee2.setFirstName(FN_2);
		employee2.setLastName(LN_2);
		
		List<Employee> employeeList = Arrays.asList(employee1, employee2);
		
		mockedStaticClass.when((Verification) EmployeeSpecification.withAtributes(filter)).thenReturn(specification);
        when(employeeRepository.findAll(specification)).thenReturn(employeeList);
		
		//Act
		List<EmployeeDTO> result = employeeService.getAll(filter);
		
		//Assert
		assertThat(result).hasSize(2);
		assertThat(result.get(0).getId()).isEqualTo(EMPLOYEE_ID);
		assertThat(result.get(0).getCpf()).isEqualTo(EMPLOYEE_CPF);
		assertThat(result.get(1).getId()).isEqualTo(EMPLOYEE_ID2);
		assertThat(result.get(1).getCpf()).isEqualTo(EMPLOYEE_CPF2);
		verify(employeeRepository, times(1)).findAll(specification); 
		
		mockedStaticClass.close();
	}
	
	@Test
	void testCreate() {
		//Arrange
		mockStatic(EmployeeMapper.class);
		
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setFirstName(FN_1);
		employeeDTO.setLastName(LN_1);
		employeeDTO.setCpf(EMPLOYEE_CPF);
		
		Employee employeeEntity = new Employee();
		employeeEntity.setFirstName(FN_1);
		employeeEntity.setLastName(LN_1);
		employeeEntity.setCpf(EMPLOYEE_CPF);
		
		when(EmployeeMapper.covertToEntity(employeeDTO)).thenReturn(employeeEntity);
		when(employeeRepository.save(employeeEntity)).thenReturn(employeeEntity);
		
		//Act
		employeeService.create(employeeDTO);
		
		//Assert
		verify(employeeRepository, times(1)).save(employeeEntity);
	}
	
	@Test
	void testCreate_WhenNameIsMissing() {
		//Arrange
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setFirstName(null);
		
		//Act e Assert
		assertThrows(EmployeeNameMissingException.class, () -> {
			employeeService.create(employeeDTO);
		});
	}
	
	@Test
	void testCreate_WhenDTOIsNull() {
		//Arrange
		EmployeeDTO employeeDTO = null;
		
		//Act e Assert
		assertThrows(EmployeeDTOMissingException.class, () -> {
			employeeService.create(employeeDTO);
		});
	}
	
	@Test
	void testUpdate() {
		//Arrange
		MockedStatic<EmployeeDTO> mockedStaticClass = mockStatic(EmployeeDTO.class);
		String responseMessage = "Employee of ID " + EMPLOYEE_ID + " updated successfully!";
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setId(EMPLOYEE_ID);
		employeeDTO.setFirstName(FN_2);
		employeeDTO.setLastName(LN_2);
		employeeDTO.setCpf(EMPLOYEE_CPF);
		employeeDTO.setCategoryId(1L);
		
		Category category = new Category();
		category.setId(1L);
		category.setDescription("Intern");
		
		Employee employeeEntityToBeUpdated = new Employee();
		employeeEntityToBeUpdated.setId(EMPLOYEE_ID);
		employeeEntityToBeUpdated.setFirstName(FN_1);
		employeeEntityToBeUpdated.setLastName(LN_1);
		employeeEntityToBeUpdated.setCpf(EMPLOYEE_CPF);
	
		when(employeeRepository.findById(employeeDTO.getId())).thenReturn(Optional.of(employeeEntityToBeUpdated));
		when(categoryRepository.findById(employeeDTO.getCategoryId())).thenReturn(Optional.of(category));
		mockedStaticClass.when((Verification) EmployeeDTO.convertToDTO(employeeEntityToBeUpdated)).thenReturn(employeeDTO);
		Mockito.doNothing().when(mockedSpy).create(employeeDTO); 
		when(EmployeeMapper.covertToEntity(employeeDTO)).thenReturn(employeeEntityToBeUpdated);
		
		//Act
		String result = employeeService.update(employeeDTO);
		
		//Assert
		verify(employeeRepository, times(1)).findById(EMPLOYEE_ID);
		verify(categoryRepository, times(1)).findById(employeeDTO.getId());
		assertThat(result).isEqualTo(responseMessage);
		mockedStaticClass.close();
	}
	
	@Test
	void testUpdate_WhenEmployeeNotFound() {
		//Arrange
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setFirstName(FN_1);
		employeeDTO.setLastName(LN_1);
		employeeDTO.setCpf(EMPLOYEE_CPF);
		employeeDTO.setId(EMPLOYEE_ID);
		
		Employee employeeEntity = new Employee();
		
		when(employeeRepository.findById(EMPLOYEE_ID)).thenReturn(Optional.of(employeeEntity));
		
		//Act
		String result = employeeService.update(employeeDTO);
		
		//Assert
		assertThat(result).isEqualTo("Collaborator of ID " + EMPLOYEE_ID + " not found");
		verify(mockedSpy, times(0)).create(any(EmployeeDTO.class)); 
	}
	
	@Test
	void testDelete() {
		//Arrange
		Employee employeeToBeDeleted = new Employee();
		employeeToBeDeleted.setId(EMPLOYEE_ID);
		when(employeeRepository.findById(EMPLOYEE_ID)).thenReturn(Optional.of(employeeToBeDeleted));
		
		//Act
		employeeService.delete(EMPLOYEE_ID);
		
		//Assert
		verify(employeeRepository, times(1)).findById(EMPLOYEE_ID);
		verify(employeeRepository, times(1)).deleteById(EMPLOYEE_ID);
	}
	
}
