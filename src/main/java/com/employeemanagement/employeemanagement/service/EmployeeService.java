package com.employeemanagement.employeemanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeemanagement.employeemanagement.dto.EmployeeDTO;
import com.employeemanagement.employeemanagement.entity.Employee;
import com.employeemanagement.employeemanagement.entity.EmployeeTraining;
import com.employeemanagement.employeemanagement.entity.EmployeeTrainingId;
import com.employeemanagement.employeemanagement.entity.Status;
import com.employeemanagement.employeemanagement.entity.Training;
import com.employeemanagement.employeemanagement.exception.EmployeeDTOMissingException;
import com.employeemanagement.employeemanagement.exception.EmployeeNameMissingException;
import com.employeemanagement.employeemanagement.repository.EmployeeRepository;
import com.employeemanagement.employeemanagement.repository.EmployeeTrainingRepository;
import com.employeemanagement.employeemanagement.repository.TrainingRepository;
import com.employeemanagement.employeemanagement.utils.EmployeeMapper;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private TrainingRepository trainingRepository;

	@Autowired
	private EmployeeTrainingRepository employeeTrainingRepository;

	@Autowired
	private EmployeeMapper employeeMapper;

	public Employee getById(Long id) {
		return getEmployeeRepository().findById(id).orElse(null);
	}

	public List<Employee> getAll() {
		List<Employee> employeeList = getEmployeeRepository().findAll();

		if (!employeeList.isEmpty()) {
			return employeeList;
		} else {
			return null;
		}
	}

	public void create(EmployeeDTO employeeDTO) {
		if (employeeDTO != null) {
			if (employeeDTO.getFirstName() == null) {
				throw new EmployeeNameMissingException();
			} else {
				Employee employee = getEmployeeMapper().covertToEntity(employeeDTO);
				getEmployeeRepository().save(employee);
				for (Long et : employeeDTO.getTrainings()) {

					Training training = new Training(et);
					Status status = new Status((long) 1);
					Employee employe = new Employee(employee.getId());
					EmployeeTrainingId employeeTrainingId = new EmployeeTrainingId(employee.getId(), et);
					EmployeeTraining employeeTraining = new EmployeeTraining(employeeTrainingId, status, employe,
							training);
					getEmployeeTrainingRepository().save(employeeTraining);
				}
			}
		} else {
			throw new EmployeeDTOMissingException();
		}
	}

	public String update(EmployeeDTO employeeDTO) {
		Employee defaultEmployee = getById(employeeDTO.getId());
		String responseMessage = "Employee of ID " + employeeDTO.getId() + " not found";

		if (defaultEmployee != null) {
			defaultEmployee.setFirstName(
					employeeDTO.getFirstName() != null ? employeeDTO.getFirstName() : defaultEmployee.getFirstName());
			defaultEmployee.setMiddleName(employeeDTO.getMiddleName() != null ? employeeDTO.getMiddleName()
					: defaultEmployee.getMiddleName());
			defaultEmployee.setLastName(
					employeeDTO.getLastName() != null ? employeeDTO.getLastName() : defaultEmployee.getLastName());
			defaultEmployee.setCpf(employeeDTO.getCpf() != null ? employeeDTO.getCpf() : defaultEmployee.getCpf());
			getEmployeeRepository().save(defaultEmployee);

			for (Long id : employeeDTO.getTrainings()) {
				Training training = new Training(id);
				Status status = new Status((long) 1);
				Employee employe = new Employee(defaultEmployee.getId());
				EmployeeTrainingId employeeTrainingId = new EmployeeTrainingId(defaultEmployee.getId(), id);
				EmployeeTraining employeeTraining = new EmployeeTraining(employeeTrainingId, status, employe, training);
				getEmployeeTrainingRepository().save(employeeTraining);
			}
			responseMessage = "Employee of ID " + employeeDTO.getId() + " updated successfully!";
		}
		return responseMessage;
	}

	public void delete(Long id) {
		Employee employee = getById(id);
		for (EmployeeTraining employeeTraining :  getEmployeeTrainingRepository().getByEmployee(employee)) {
			getEmployeeTrainingRepository().delete(employeeTraining);
		}
		getEmployeeRepository().deleteById(id);
	}

	public void updateStartTraining(Long idEmployee, Long idTraining) {

		Employee employee = getEmployeeRepository().findById(idEmployee).orElse(null);
		Training training = getTrainingRepository().findById(idTraining).orElse(null);
		EmployeeTrainingId employeeTrainingId = new EmployeeTrainingId(employee.getId(), training.getID());
		EmployeeTraining employeeTraining = getEmployeeTrainingRepository().findById(employeeTrainingId).orElse(null);
		if (idEmployee != null && idTraining != null) {
			Status status = new Status((long) 4); // Id de Status Iniciado
			employeeTraining.setStatus(status);
			getEmployeeTrainingRepository().save(employeeTraining);
		} else {
			throw new EmployeeDTOMissingException();

		}
	}

	public void updateFinishTraining(Long idEmployee, Long idTraining) {

		Employee employee = getEmployeeRepository().findById(idEmployee).orElse(null);
		Training training = getTrainingRepository().findById(idTraining).orElse(null);
		EmployeeTrainingId employeeTrainingId = new EmployeeTrainingId(employee.getId(), training.getID());
		EmployeeTraining employeeTraining = getEmployeeTrainingRepository().findById(employeeTrainingId).orElse(null);
		if (idEmployee != null && idTraining != null) {
			Status status = new Status((long) 3); // Id de Status finalizado
			employeeTraining.setStatus(status);
			getEmployeeTrainingRepository().save(employeeTraining);
		} else {
			throw new EmployeeDTOMissingException();

		}
	}

	private EmployeeTrainingRepository getEmployeeTrainingRepository() {
		return employeeTrainingRepository;
	}

	private EmployeeRepository getEmployeeRepository() {
		return employeeRepository;
	}

	private TrainingRepository getTrainingRepository() {
		return trainingRepository;
	}

	private EmployeeMapper getEmployeeMapper() {
		return employeeMapper;
	}

}
