package com.employeemanagement.employeemanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeemanagement.employeemanagement.dto.EmployeeDTO;
import com.employeemanagement.employeemanagement.entity.Category;
import com.employeemanagement.employeemanagement.entity.Employee;
import com.employeemanagement.employeemanagement.repository.EmployeeRepository;
import com.employeemanagement.employeemanagement.utils.EmployeeMapper;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private EmployeeMapper employeeMapper;

	public Employee getById(Long id) {	 
		return getEmployeeRepository().findById(id).orElse(null);
	}

	public void create(EmployeeDTO dto) {
		if (dto != null) {
			Employee entity = getEmployeeMapper().covertToEntity(dto);
			Category category = getCategoryService().finById(dto.getCategory().getId());

			entity.setCategory(category);

			getEmployeeRepository().save(entity);

		} else {
			throw new IllegalArgumentException("Atributes cannot by null");

		}
	}

	public List<EmployeeDTO> getAll() {
		List<Employee> employeeList = getEmployeeRepository().findAll();
		List<EmployeeDTO> employeeListDTO = employeeList.stream().map(employee -> EmployeeDTO.convertToDTO(employee))
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
			defaultEmployee.setMiddleName(employeeDTO.getMiddleName() != null ? employeeDTO.getMiddleName(): defaultEmployee.getMiddleName());
			defaultEmployee.setLastName(employeeDTO.getLastName() != null ? employeeDTO.getLastName() : defaultEmployee.getLastName());
			defaultEmployee.setCpf(employeeDTO.getCpf() != null ? employeeDTO.getCpf() : defaultEmployee.getCpf());
			Category category = getCategoryService().finById(employeeDTO.getCategory().getId());
			defaultEmployee.setCategory(category);
			getEmployeeRepository().save(defaultEmployee);
			responseMessage = "Employee of ID " + employeeDTO.getId() + " updated successfully!";
			return responseMessage;
		}
		return responseMessage;
	}

	public String delete(Long id) {
		Employee employee = getById(id);

		if (employee == null) {
			return "This employee ID " + id + " doesn't exist";
		} else {
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

}
