package com.employeemanagement.employeemanagement.test;

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

import com.employeemanagement.employeemanagement.controller.EmployeeController;
import com.employeemanagement.employeemanagement.dto.EmployeeDTO;
import com.employeemanagement.employeemanagement.dto.EmployeeFilterDTO;
import com.employeemanagement.employeemanagement.service.EmployeeService;


//É necessário importar esses métodos estáticos
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

//Annotation para testes jUnit, testando em controladores/controllers
@SpringBootTest
public class EmployeeControllerTest {
	
	private static final String ILLEGAL_ARGUMENT_ERROR = "Argument cannot be null";
	private static final String DATABASE_ERROR = "Database error";
	private static final Long EMPLOYEE_ID = 1L;
	private static final Long TRAINING_ID = 1L;

	@Mock
	@Autowired
	private EmployeeService employeeService;
	
	@InjectMocks
	@Autowired
	private EmployeeController employeeController;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	void testGetAll() {
		//Arrange
		//Filtro mockado que será passado para teste
		EmployeeFilterDTO filter = new EmployeeFilterDTO();
		filter.setFirstName("Test");
		
		//Resultados mockados esperados do teste do filtro
		EmployeeDTO employee1 = new EmployeeDTO();
		employee1.setFirstName("Test");
		employee1.setCpf("444");
		
		EmployeeDTO employee2 = new EmployeeDTO();
		employee2.setFirstName("Test");
		employee2.setCpf("123");
		
		//Condição
		List<EmployeeDTO> employeeListDTO = Arrays.asList(employee1, employee2);
		when(employeeService.getAll(filter)).thenReturn(employeeListDTO);
		
		//Act
		ResponseEntity<List<EmployeeDTO>> testResponse = (ResponseEntity<List<EmployeeDTO>>) employeeController.getAll(filter); 
		
		//Assert
		assertThat(testResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(testResponse.getBody()).hasSameClassAs(employeeListDTO); //Testando para ver se o tipo de classe é o mesmo comparado
		assertThat(testResponse.getBody().get(0).getFirstName()).isEqualTo("Test");
		assertThat(testResponse.getBody().get(1).getFirstName()).isEqualTo("Test");
		verify(employeeService, times(1)).getAll(filter);
	}
	
	@Test
	void testGetAll_WhenControllerThrowsException(){
		
		//Gerando exceção genérica para ser verificada MOCKADA
		EmployeeFilterDTO filter = new EmployeeFilterDTO();
        when(employeeService.getAll(filter)).thenThrow(new RuntimeException(DATABASE_ERROR));
        
        //Act
		ResponseEntity<?> testResponse = employeeController.getAll(filter); 
        
		//Assert
		assertThat(testResponse.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
		assertThat(testResponse.getBody()).isEqualTo("Internal Server Error: " + DATABASE_ERROR);
		verify(employeeService, times(1)).getAll(filter);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	void testGetById() {
		//Arrange
		EmployeeDTO employee1 = new EmployeeDTO();
		employee1.setFirstName("Test");
		employee1.setId(EMPLOYEE_ID);
		
		when(employeeService.getById(EMPLOYEE_ID)).thenReturn(employee1);
		
		//Act
		ResponseEntity<EmployeeDTO> testResponse = (ResponseEntity<EmployeeDTO>) employeeController.getById(EMPLOYEE_ID); 
		
		//Assert
		assertThat(testResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(testResponse.getBody()).hasSameClassAs(employee1);
		assertThat(testResponse.getBody().getFirstName()).isEqualTo("Test");
		verify(employeeService, times(1)).getById(EMPLOYEE_ID);
	}
	
	@Test
	void testGetById_WhenControllerThrowsException() {
		//Arrange
		Long employeeId = 1L;
		
		//Gerando exceção genérica para ser verificada MOCKADA
        when(employeeService.getById(employeeId)).thenThrow(new RuntimeException(DATABASE_ERROR));
        
        //Act
		ResponseEntity<?> testResponse = employeeController.getById(employeeId); 
		
		//Assert
		assertThat(testResponse.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
		assertThat(testResponse.getBody()).isEqualTo(DATABASE_ERROR);
		verify(employeeService, times(1)).getById(employeeId);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	void testCreate() {
		//Arrange - montar os dados mockados, no caso o DTO/entidade que será salvo sem situação WHEN
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setFirstName("Teste");
		employeeDTO.setCpf("123");
		employeeDTO.setLastName("Teste");
		employeeDTO.setPhoto(null);
		employeeDTO.setCategoryId(1L);
		List<Long> trainingsId = Arrays.asList(1L, 2L, 3L);
		employeeDTO.setTrainingsId(trainingsId);
		
		//Act
		ResponseEntity<String> testResponse = (ResponseEntity<String>) employeeController.create(employeeDTO); 

		//Assert
		assertThat(testResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(testResponse.getBody()).isEqualTo("employee inserted successfully!");
		verify(employeeService, times(1)).create(employeeDTO);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	void testCreate_WhenControllerThrowsException() {
		//Arrange
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setFirstName("Teste Exception");
		
		//Gerando exceção genérica para ser verificada MOCKADA, o comando doThrow é usado para 
		//métodos que não retornam nada (VOID)
        doThrow(new RuntimeException(DATABASE_ERROR)).when(employeeService).create(employeeDTO);
        
        //Act
		ResponseEntity<String> testResponse = (ResponseEntity<String>) employeeController.create(employeeDTO); 

		//Assert
		assertThat(testResponse.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
		assertThat(testResponse.getBody()).isEqualTo("Failed trying to insert new data, error message: " + DATABASE_ERROR);
		verify(employeeService, times(1)).create(employeeDTO);
	}
	
	@Test
	void testUpdate() {
		//Arrange
		String responseMessage = "Employee of ID " + EMPLOYEE_ID + " updated successfully!";
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setId(EMPLOYEE_ID);
		
		when(employeeService.update(employeeDTO)).thenReturn(responseMessage);
		
		//Act
		ResponseEntity<String> testResponse = employeeController.update(employeeDTO); 

		//Assert
		assertThat(testResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(testResponse.getBody()).isEqualTo(responseMessage);
		verify(employeeService, times(1)).update(employeeDTO);
	}
	
	@Test
	void testUpdate_SecondReturnCondition() {
		//Arrange
		String responseMessage = "Collaborator of ID " + EMPLOYEE_ID + " not found";
		EmployeeDTO employeeDTO = new EmployeeDTO();
		
		when(employeeService.update(employeeDTO)).thenReturn(responseMessage);
		
		//Act
		ResponseEntity<String> testResponse = employeeController.update(employeeDTO); 

		//Assert
		assertThat(testResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(testResponse.getBody()).isEqualTo(responseMessage);
		verify(employeeService, times(1)).update(employeeDTO);
	}
	
	@Test
	void testUpdate_WhenControllerThrowsException() {
		//Arrange
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setFirstName("Teste Exception");
		
        doThrow(new RuntimeException(DATABASE_ERROR)).when(employeeService).update(employeeDTO);
        
        //Act
		ResponseEntity<String> testResponse = (ResponseEntity<String>) employeeController.update(employeeDTO); 

		//Assert
		assertThat(testResponse.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
		assertThat(testResponse.getBody()).isEqualTo("Error trying to update employee. Error message: " + DATABASE_ERROR);
		verify(employeeService, times(1)).update(employeeDTO);
	}
	
	@Test
	void testDelete() {
		//Act
		ResponseEntity<String> testResponse = employeeController.delete(EMPLOYEE_ID);
		
		//Assert
		assertThat(testResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(testResponse.getBody()).isEqualTo("employee deleted succesfully");
		verify(employeeService, times(1)).delete(EMPLOYEE_ID);
	}
	
	@Test
	void testDelete_WhenControllerThrowsException() {
		//Arrange
		doThrow(new IllegalArgumentException(ILLEGAL_ARGUMENT_ERROR)).when(employeeService).delete(null);
		
		//Act
		ResponseEntity<String> testResponse = employeeController.delete(null);
		
		//Assert
		assertThat(testResponse.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
		assertThat(testResponse.getBody()).isEqualTo("Internal error, message: " + ILLEGAL_ARGUMENT_ERROR);
		verify(employeeService, times(1)).delete(null);
	}
	
	@Test 
	void testStartTraining() {
		//Arrange
		Mockito.doNothing().when(employeeService).startTraining(EMPLOYEE_ID, TRAINING_ID);
		
		//Act
		ResponseEntity<String> testResponse = employeeController.startTraining(EMPLOYEE_ID, TRAINING_ID);
		
		//Assert
		assertThat(testResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(testResponse.getBody()).isEqualTo("Training Started Sucessfully !");
		verify(employeeService, times(1)).startTraining(EMPLOYEE_ID, TRAINING_ID);
	}
	
	@Test
	void testStartTraining_WhenThrowsException() {
		//Arrange
        doThrow(new RuntimeException(DATABASE_ERROR)).when(employeeService).startTraining(EMPLOYEE_ID, TRAINING_ID);
        
        //Act
		ResponseEntity<String> testResponse = employeeController.startTraining(EMPLOYEE_ID, TRAINING_ID);

		//Assert
		assertThat(testResponse.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
		assertThat(testResponse.getBody()).isEqualTo("Error trying to update Status. Error message: " + DATABASE_ERROR);
		verify(employeeService, times(1)).startTraining(EMPLOYEE_ID, TRAINING_ID);
	}
	
	@Test 
	void testFinishTraining() {
		//Arrange
		Mockito.doNothing().when(employeeService).finishTraining(EMPLOYEE_ID, TRAINING_ID);
		
		//Act
		ResponseEntity<String> testResponse = employeeController.finishTraining(EMPLOYEE_ID, TRAINING_ID);
		
		//Assert
		assertThat(testResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(testResponse.getBody()).isEqualTo("Training Finished Sucessfully !");
		verify(employeeService, times(1)).finishTraining(EMPLOYEE_ID, TRAINING_ID);
	}
	
	@Test
	void testFinishTraining_WhenThrowsException() {
		//Arrange
        doThrow(new RuntimeException(DATABASE_ERROR)).when(employeeService).finishTraining(EMPLOYEE_ID, TRAINING_ID);
        
        //Act
		ResponseEntity<String> testResponse = employeeController.finishTraining(EMPLOYEE_ID, TRAINING_ID);

		//Assert
		assertThat(testResponse.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
		assertThat(testResponse.getBody()).isEqualTo("Error trying to update Status. Error message: " + DATABASE_ERROR);
		verify(employeeService, times(1)).finishTraining(EMPLOYEE_ID, TRAINING_ID);
	}
	
}
