package com.employeemanagement.employeemanagement.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.employeemanagement.employeemanagement.repository.EmployeeRepository;
import com.employeemanagement.employeemanagement.service.QualificationService;

@SpringBootTest
public class QualificationServiceTest {
	
	@Mock
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@InjectMocks
	@Autowired
	private QualificationService qualificationService;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void testGetById() {
		
	}
	
	@Test
	void testGetAll() {
		
	}


}
