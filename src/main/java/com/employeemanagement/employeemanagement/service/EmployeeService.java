package com.employeemanagement.employeemanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeemanagement.employeemanagement.dto.EmployeeDTO;
import com.employeemanagement.employeemanagement.dto.EmployeeDTOFilter;
import com.employeemanagement.employeemanagement.entity.Category;
import com.employeemanagement.employeemanagement.entity.Employee;
import com.employeemanagement.employeemanagement.entity.EmployeeTraining;
import com.employeemanagement.employeemanagement.entity.EmployeeTrainingPK;
import com.employeemanagement.employeemanagement.entity.Training;
import com.employeemanagement.employeemanagement.repository.EmployeeRepository;
import com.employeemanagement.employeemanagement.repository.EmployeeTrainingRepository;
import com.employeemanagement.employeemanagement.utils.EmployeeMapper;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private TrainingService trainingService;
	
	@Autowired
	private EmployeeTrainingRepository employeeTrainingRepository;

	@Autowired
	private EmployeeMapper employeeMapper;

	public Employee getById(Long id) {
		return getEmployeeRepository().findById(id).orElse(null);
	}

	public void create(EmployeeDTO dto) {
	    if (dto == null || dto.getTrainingsID() == null || dto.getTrainingsID().isEmpty()) {
	        throw new IllegalArgumentException("Attributes cannot be null or empty");
	    }
	    Employee entity = getEmployeeMapper().covertToEntity(dto);
	    entity.setTrainings(new ArrayList<>());
	    
	    Category category = getCategoryService().finById(dto.getCategory().getId());
	    if (category != null) {
	        entity.setCategory(category);

	        getEmployeeRepository().save(entity);

	        for (Long trainingId : dto.getTrainingsID()) {
	            Training training = getTrainingService().getById(trainingId);
	            if (training != null) {
	            	EmployeeTrainingPK employeeTrainingPK = new EmployeeTrainingPK(entity.getId(), trainingId);
	                EmployeeTraining employeeTraining = new EmployeeTraining();
	                employeeTraining.setId(employeeTrainingPK);
	                employeeTraining.setEmployee(entity);
	                employeeTraining.setTraining(training);
	                
	                getEmployeeTrainingRepository().save(employeeTraining);
	            }
	        }
	        getEmployeeRepository().save(entity);
	    } else {
	        throw new IllegalArgumentException("Category not found");
	    }
	}


	public List<EmployeeDTOFilter> getAll() {
		List<Employee> employeeList = getEmployeeRepository().findAll();
		List<EmployeeDTOFilter> employeeListDTO = employeeList.stream().map(employee -> EmployeeDTOFilter.convertToDTO(employee))
				.toList();

		if (!employeeListDTO.isEmpty()) {
			return employeeListDTO;
		} else {
			return null;
		}
	}

	public String update(EmployeeDTO employeeDTO) {
	    Employee defaultEmployee = getById(employeeDTO.getId());
	    String responseMessage = "Collaborator of ID " + employeeDTO.getId() + " not found";

	    if (defaultEmployee != null) {
	        defaultEmployee.setFirstName(employeeDTO.getFirstName() != null ? employeeDTO.getFirstName() : defaultEmployee.getFirstName());
	        defaultEmployee.setMiddleName(employeeDTO.getMiddleName() != null ? employeeDTO.getMiddleName() : defaultEmployee.getMiddleName());
	        defaultEmployee.setLastName(employeeDTO.getLastName() != null ? employeeDTO.getLastName() : defaultEmployee.getLastName());
	        defaultEmployee.setCpf(employeeDTO.getCpf() != null ? employeeDTO.getCpf() : defaultEmployee.getCpf());

	        List<Long> newTrainingIds = employeeDTO.getTrainingsID();

	        for (Long trainingId : newTrainingIds) {
	            Training training = getTrainingService().getById(trainingId);
	            if (training != null) {
	                EmployeeTrainingPK employeeTrainingPK = new EmployeeTrainingPK(defaultEmployee.getId(), trainingId);
	                EmployeeTraining employeeTraining = new EmployeeTraining();
	                employeeTraining.setId(employeeTrainingPK);
	                employeeTraining.setEmployee(defaultEmployee);
	                employeeTraining.setTraining(training);
	                getEmployeeTrainingRepository().save(employeeTraining);
	            } else {
	                throw new IllegalArgumentException("Training with ID " + trainingId + " not found");
	            }
	        }

	        // Salva a entidade Employee atualizada
	        getEmployeeRepository().save(defaultEmployee);
	        responseMessage = "Employee of ID " + employeeDTO.getId() + " updated successfully!";
	    }

	    return responseMessage;
	}



	public String delete(Long id) {
	    Employee employee = getById(id);

	    if (employee == null) {
	        return "This employee ID " + id + " doesn't exist";
	    } else {
	        List<EmployeeTraining> employeeTrainings = employee.getTrainings();
	        for (EmployeeTraining employeeTraining : employeeTrainings) {
	            getEmployeeTrainingRepository().delete(employeeTraining);
	        }

	        getEmployeeRepository().deleteById(id);
	        return "Employee of ID " + id + " removed!";
	    }
	}

	private EmployeeRepository getEmployeeRepository() {
		return employeeRepository;
	}

	private EmployeeMapper getEmployeeMapper() {
		return employeeMapper;
	}

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public TrainingService getTrainingService() {
		return trainingService;
	}

	public EmployeeTrainingRepository getEmployeeTrainingRepository() {
		return employeeTrainingRepository;
	}

}
