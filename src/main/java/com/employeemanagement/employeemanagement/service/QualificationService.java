package com.employeemanagement.employeemanagement.service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.employeemanagement.employeemanagement.dto.CategoryDTO;
import com.employeemanagement.employeemanagement.dto.EmployeeFilterDTO;
import com.employeemanagement.employeemanagement.dto.EmployeeTrainingDTO;
import com.employeemanagement.employeemanagement.dto.QualificationDTO;
import com.employeemanagement.employeemanagement.entity.Employee;
import com.employeemanagement.employeemanagement.entity.EmployeeTraining;
import com.employeemanagement.employeemanagement.repository.CategoryRepository;
import com.employeemanagement.employeemanagement.repository.EmployeeRepository;
import com.employeemanagement.employeemanagement.utils.EmployeeSpecification;

@Service
public class QualificationService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<QualificationDTO> getAll(EmployeeFilterDTO employeeFilterDTO) {
		Specification<Employee> specification = EmployeeSpecification.withAtributes(employeeFilterDTO);
		List<Employee> employeeList = getEmployeeRepository().findAll(specification);
		List<QualificationDTO> qualifications = new ArrayList<QualificationDTO>();
		
		if(employeeList.isEmpty() == false) {
			for(Employee employee : employeeList) {
				qualifications.add(buildQualification(employee));
			}
		}
		return qualifications;
	}
	
	public QualificationDTO getById(Long id) {
		Employee employee = getEmployeeRepository().findById(id).orElse(null);
		if(employee != null) {
			return buildQualification(employee);
		}
		return null;
	}
	
	private QualificationDTO buildQualification(Employee employee) {
		QualificationDTO qualificationDTO = new QualificationDTO();
		qualificationDTO.setId(employee.getId());
		qualificationDTO.setFullName(employee.getFullName());
		qualificationDTO.setStatusTrainings(null);
		qualificationDTO.setCategory(CategoryDTO.convertToDTO(employee.getCategory()));
		
		for(EmployeeTraining relationship : employee.getTrainings()) {
			EmployeeTrainingDTO relationshipDTO = EmployeeTrainingDTO.convertToDTO(relationship);
			if(qualificationDTO.getTrainings() == null) {
				List<EmployeeTrainingDTO> relationshipList = new ArrayList<EmployeeTrainingDTO>();
				qualificationDTO.setTrainings(relationshipList);
			}
			qualificationDTO.getTrainings().add(relationshipDTO);
		}

		if(employee.getPhoto() != null) {
			qualificationDTO.setPhoto(base64Converter(employee.getPhoto()));
		}
		
		return qualificationDTO;
	}
	
	private String base64Converter(byte[] img) {
		return Base64.getEncoder().encodeToString(img);
	}

	private EmployeeRepository getEmployeeRepository() {
		return employeeRepository;
	}

	public CategoryRepository getCategoryRepository() {
		return categoryRepository;
	}

}
