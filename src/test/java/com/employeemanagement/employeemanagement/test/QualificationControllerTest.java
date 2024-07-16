package com.employeemanagement.employeemanagement.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.employeemanagement.employeemanagement.controller.QualificationController;
import com.employeemanagement.employeemanagement.dto.EmployeeFilterDTO;
import com.employeemanagement.employeemanagement.dto.QualificationDTO;
import com.employeemanagement.employeemanagement.service.QualificationService;

@SpringBootTest
public class QualificationControllerTest {
	
	private static final Long QUALIFICATION_ID = 1L;
	private static final Long QUALIFICATION_ID2 = 2L;
	private static final String FULLNAME1 = "Qualification 1";
	private static final String FULLNAME2 = "Qualification 2";
	
	@Mock
	@Autowired
	private QualificationService qualificationService;
	
	@InjectMocks
	@Autowired
	private QualificationController qualificationController;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
//	@SuppressWarnings("unchecked")
//	@Test
//	void testGetById() {
//		QualificationDTO qualificationDTO = new QualificationDTO();
//		qualificationDTO.setId(QUALIFICATION_ID);
//		qualificationDTO.setFullName(FULLNAME1);
//		qualificationDTO.setPhoto(null);
//		
//		when(qualificationService.getById(QUALIFICATION_ID)).thenReturn(qualificationDTO);
//		
//		ResponseEntity<QualificationDTO> testResponse = (ResponseEntity<QualificationDTO>) qualificationController.getById(QUALIFICATION_ID);
//		
//		assertThat(testResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
//		assertThat(testResponse.getBody()).hasSameClassAs(qualificationDTO);
//		assertThat(testResponse.getBody().getFullName()).isEqualTo(FULLNAME1);
//		assertThat(testResponse.getBody().getId()).isEqualTo(QUALIFICATION_ID);
//		verify(qualificationService, times(1)).getById(QUALIFICATION_ID);
//	}
//	
//	@SuppressWarnings("unchecked")
//	@Test
//	void testGetAll() {
//		QualificationDTO qualificationDTO = new QualificationDTO();
//		qualificationDTO.setId(QUALIFICATION_ID);
//		qualificationDTO.setFullName(FULLNAME1);
//		qualificationDTO.setPhoto(null);
//		
//		QualificationDTO qualificationDTO2 = new QualificationDTO();
//		qualificationDTO2.setId(QUALIFICATION_ID2);
//		qualificationDTO2.setFullName(FULLNAME2);
//		qualificationDTO2.setPhoto(null);
//		
//		List<QualificationDTO> qualificationListDTO = Arrays.asList(qualificationDTO, qualificationDTO2);
//		
//		EmployeeFilterDTO employeeFilterDTO = new EmployeeFilterDTO();
//		
//		when(qualificationService.getAll(employeeFilterDTO)).thenReturn(qualificationListDTO);
//		
//		ResponseEntity<List<QualificationDTO>> testResponse = (ResponseEntity<List<QualificationDTO>>) qualificationController.getAll(employeeFilterDTO);
//		
//		assertThat(testResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
//		assertThat(testResponse.getBody()).hasSize(2);
//		assertThat(testResponse.getBody().get(0).getFullName()).isEqualTo(FULLNAME1);
//		assertThat(testResponse.getBody().get(1).getFullName()).isEqualTo(FULLNAME2);
//		verify(qualificationService, times(1)).getAll(employeeFilterDTO);
//	}

}
