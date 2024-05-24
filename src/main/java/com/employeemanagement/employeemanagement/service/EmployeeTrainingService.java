package com.employeemanagement.employeemanagement.service;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import com.employeemanagement.employeemanagement.entity.Employee;
//import com.employeemanagement.employeemanagement.entity.EmployeeTraining;
//import com.employeemanagement.employeemanagement.entity.Training;
//import com.employeemanagement.employeemanagement.repository.EmployeeRepository;
//import com.employeemanagement.employeemanagement.repository.EmployeeTrainingRepository;
//import com.employeemanagement.employeemanagement.repository.TrainingRepository;

@Service
public class EmployeeTrainingService {
	
//	@Autowired
//	private EmployeeRepository employeeRepository;
//	
//	@Autowired
//	private TrainingRepository trainingRepository;
//	
//	@Autowired
//	private EmployeeTrainingRepository employeeTrainingRepository;
//	
//	public void addTrainingToEmployee (Long employeeId, Long trainingId) {
//		Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new IllegalArgumentException("Invalid Employee Id: " + employeeId));
//		
//		Training training = trainingRepository.findById(trainingId).orElseThrow(() -> new IllegalArgumentException("Invalid Training Id: " + trainingId));
//		
//		EmployeeTraining employeeTraining = new EmployeeTraining(employee, training);
//		employeeTrainingRepository.save(employeeTraining);		
//	}
}
