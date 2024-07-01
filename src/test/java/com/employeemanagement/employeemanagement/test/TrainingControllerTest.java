package com.employeemanagement.employeemanagement.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.employeemanagement.employeemanagement.controller.TrainingController;
import com.employeemanagement.employeemanagement.dto.TrainingDTO;
import com.employeemanagement.employeemanagement.entity.Training;
import com.employeemanagement.employeemanagement.service.TrainingService;

@SpringBootTest
public class TrainingControllerTest {
	
	private static final Long TRAINING_ID = 1L;
	private static final String TRAINING_DESCRIPTION = "Unit Test basic training developed in Java";
	private static final String DATABASE_ERROR = "Database error";
	
	@Mock
	@Autowired
	private TrainingService trainingService;
	
	@InjectMocks
	@Autowired
	private TrainingController trainingController;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	void testGetById() {
		//Arrange
		Training training = new Training();
		training.setTitle("Unit Test");
		training.setId(TRAINING_ID);
		training.setDescription(TRAINING_DESCRIPTION);
		
		TrainingDTO trainingDTO = new TrainingDTO();
		trainingDTO.setTitle("Unit Test");
		trainingDTO.setId(TRAINING_ID);
		trainingDTO.setDescription(TRAINING_DESCRIPTION);
	
		when(trainingService.getById(TRAINING_ID)).thenReturn(training);
		
		//Act
		ResponseEntity<TrainingDTO> testResponse = (ResponseEntity<TrainingDTO>) trainingController.getById(TRAINING_ID); 
		
		//Assert
		assertThat(testResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(testResponse.getBody()).hasSameClassAs(trainingDTO);
		assertThat(testResponse.getBody().getTitle()).isEqualTo("Unit Test");
		assertThat(testResponse.getBody().getDescription()).isEqualTo(TRAINING_DESCRIPTION);
		verify(trainingService, times(1)).getById(TRAINING_ID);
	}
	
	@Test
	void testGetById_WhenControllerThrowsException() {
		//Arrange
        when(trainingService.getById(TRAINING_ID)).thenThrow(new RuntimeException(DATABASE_ERROR));
        
        //Act
		ResponseEntity<?> testResponse = trainingController.getById(TRAINING_ID); 
		
		//Assert
		assertThat(testResponse.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
		assertThat(testResponse.getBody()).isEqualTo(DATABASE_ERROR);
		verify(trainingService, times(1)).getById(TRAINING_ID);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	void testGetAll() {
		//Arrange
		TrainingDTO training1 = new TrainingDTO();
		training1.setId(TRAINING_ID);
		TrainingDTO training2 = new TrainingDTO();
		training2.setId(2L);
		
		List<TrainingDTO> listTraining = Arrays.asList(training1, training2);
		
		when(trainingService.getAll()).thenReturn(listTraining);
		
		//Act
		ResponseEntity<List<TrainingDTO>> testResponse = (ResponseEntity<List<TrainingDTO>>) trainingController.getAll();
		
		//Assert
		assertThat(testResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(testResponse.getBody()).hasSameClassAs(listTraining); 
		assertThat(testResponse.getBody().get(0).getId()).isEqualTo(TRAINING_ID);
		assertThat(testResponse.getBody().get(1).getId()).isEqualTo(2L);
		verify(trainingService, times(1)).getAll();
	}
	
	@Test
	void testGetAll_WhenThrowsException() {
		//Arrange
		when(trainingService.getAll()).thenThrow(new RuntimeException(DATABASE_ERROR));
		
		//Act
		ResponseEntity<?> testResponse = trainingController.getAll();
		
		//Assert
		assertThat(testResponse.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
		assertThat(testResponse.getBody()).isEqualTo("Internal Server Error: " + DATABASE_ERROR);
		verify(trainingService, times(1)).getAll();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	void testCreate() {
		//Arrange 
		TrainingDTO trainingDTO = new TrainingDTO();
		trainingDTO.setId(TRAINING_ID);
		trainingDTO.setTitle("Unit Test");
		
		Mockito.doNothing().when(trainingService).create(trainingDTO);
		
		//Act
		ResponseEntity<String> testResponse = (ResponseEntity<String>) trainingController.create(trainingDTO); 

		//Assert
		assertThat(testResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(testResponse.getBody()).isEqualTo("Training inserted successfully!");
		verify(trainingService, times(1)).create(trainingDTO);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	void testCreate_WhenThrowsException() {
		//Arrange 
		TrainingDTO trainingDTO = new TrainingDTO();
		trainingDTO.setDescription(TRAINING_DESCRIPTION);
		
		doThrow(new RuntimeException(DATABASE_ERROR)).when(trainingService).create(trainingDTO);
		
		//Act
		ResponseEntity<String> testResponse = (ResponseEntity<String>) trainingController.create(trainingDTO); 
		
		//Assert
		assertThat(testResponse.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
		assertThat(testResponse.getBody()).isEqualTo("Failed trying to insert new data, error message: " + DATABASE_ERROR);
		verify(trainingService, times(1)).create(trainingDTO);
	}
	
	@Test
	void testUpdate() {
		//Arrange 
		String responseMessage = "Training of ID " + TRAINING_ID + " updated successfully!";
		TrainingDTO trainingDTO = new TrainingDTO();
		trainingDTO.setId(TRAINING_ID);
		
		when(trainingService.update(trainingDTO)).thenReturn(responseMessage);
		
		//Act
		ResponseEntity<String> testResponse = trainingController.update(trainingDTO); 

		//Assert
		assertThat(testResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(testResponse.getBody()).isEqualTo(responseMessage);
		verify(trainingService, times(1)).update(trainingDTO);
	}
	
	@Test
	void testUpdate_WhenThrowsException() {
		//Arrange 
		TrainingDTO trainingDTO = new TrainingDTO();
		trainingDTO.setId(TRAINING_ID);
		
		doThrow(new RuntimeException(DATABASE_ERROR)).when(trainingService).update(trainingDTO);
		
		//Act
		ResponseEntity<String> testResponse = trainingController.update(trainingDTO); 

		//Assert
		assertThat(testResponse.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
		assertThat(testResponse.getBody()).isEqualTo("Error trying to update training. Error message: " + DATABASE_ERROR);
		verify(trainingService, times(1)).update(trainingDTO);
	}
	
	@Test
	void testDelete() {
		//Act
		ResponseEntity<String> testResponse = trainingController.delete(TRAINING_ID);
		
		//Assert
		assertThat(testResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(testResponse.getBody()).isEqualTo("Training deleted succesfully");
		verify(trainingService, times(1)).delete(TRAINING_ID);
	}
	
	@Test
	void testDelete_WhenThrowsException() {
		//Arrange
		doThrow(new RuntimeException(DATABASE_ERROR)).when(trainingService).delete(TRAINING_ID);
		
		//Act
		ResponseEntity<String> testResponse = trainingController.delete(TRAINING_ID);
		
		//Assert
		assertThat(testResponse.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
		assertThat(testResponse.getBody()).isEqualTo("Internal error, message: " + DATABASE_ERROR);
		verify(trainingService, times(1)).delete(TRAINING_ID);
	}

}
