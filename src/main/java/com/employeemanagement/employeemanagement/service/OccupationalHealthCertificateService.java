package com.employeemanagement.employeemanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeemanagement.employeemanagement.dto.OccupationalHealthCertificateDTO;
import com.employeemanagement.employeemanagement.dto.OccupationalHealthCertificateTypeDTO;
import com.employeemanagement.employeemanagement.entity.Category;
import com.employeemanagement.employeemanagement.entity.Employee;
import com.employeemanagement.employeemanagement.entity.OccupationalHealthCertificate;
import com.employeemanagement.employeemanagement.entity.OccupationalHealthCertificateType;
import com.employeemanagement.employeemanagement.repository.CategoryRepository;
import com.employeemanagement.employeemanagement.repository.EmployeeRepository;
import com.employeemanagement.employeemanagement.repository.OccupationalHealthCertificateRepository;
import com.employeemanagement.employeemanagement.repository.OccupationalHealthCertificateTypeRepository;

@Service
public class OccupationalHealthCertificateService {

	@Autowired
	private OccupationalHealthCertificateRepository healthCertificateRepository;

	@Autowired
	private OccupationalHealthCertificateTypeRepository typeRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	public List<OccupationalHealthCertificateDTO> getAll() {
		// DTO
		List<OccupationalHealthCertificateDTO> healthCertificateListDTO = new ArrayList<>();
		// Entity
		List<OccupationalHealthCertificate> healthCertificateList = getHealthRepository().findAll();

		if (healthCertificateList.isEmpty() == false) {
			for (OccupationalHealthCertificate entity : healthCertificateList) {
				healthCertificateListDTO.add(OccupationalHealthCertificateDTO.convertToDTO(entity));
			}
		}

		return healthCertificateListDTO;
	}

	public OccupationalHealthCertificateDTO getById(Long id) {
		OccupationalHealthCertificateDTO healthCertificateDTO = new OccupationalHealthCertificateDTO();
		OccupationalHealthCertificate healthCertificateEntity = getHealthRepository().findById(id).orElse(null);

		if (healthCertificateEntity != null) {
			return OccupationalHealthCertificateDTO.convertToDTO(healthCertificateEntity);
		}

		return healthCertificateDTO;
	}

	public List<OccupationalHealthCertificateTypeDTO> getTypeList() {
		List<OccupationalHealthCertificateTypeDTO> typeListDTO = new ArrayList<>();
		List<OccupationalHealthCertificateType> typeList = getTypeRepository().findAll();

		if (typeList.isEmpty() == false) {
			for (OccupationalHealthCertificateType entity : typeList) {
				typeListDTO.add(OccupationalHealthCertificateTypeDTO.convertToDTO(entity));
			}
		}

		return typeListDTO;
	}

	public void create(OccupationalHealthCertificateDTO certificateDTO) {
		Employee employee = getEmployeeRepository().findById(certificateDTO.getEmployeeId())
				.orElseThrow(() -> new RuntimeException("Employee not found"));

		// Inserindo dados para entidade ASO
		// ------------------------------------------------//
		OccupationalHealthCertificate healthCertificate = new OccupationalHealthCertificate();
		healthCertificate.setEmployee(employee);
		healthCertificate.setDate(certificateDTO.getDate());
		healthCertificate.setIsAccomplished(certificateDTO.isAccomplished());
		OccupationalHealthCertificateType type = getTypeRepository()
				.findById(certificateDTO.getOccupationHealthCertificateTypeId()).orElse(null);
		healthCertificate.setOccupationHealthCertificateType(type);

		if (certificateDTO.getOccupationHealthCertificateTypeId() == 1
				|| certificateDTO.getOccupationHealthCertificateTypeId() == 4) {

			if (certificateDTO.getNewCategoryId() != null) {
				Category newCategory = getCategoryRepository().findById(certificateDTO.getNewCategoryId())
						.orElseThrow(() -> new RuntimeException("Category not found"));
				healthCertificate.setNewCategory(newCategory);
			}

		} else {
			healthCertificate.setCurrentCategory(employee.getCategory());
		}

		getHealthRepository().save(healthCertificate);
	}
	
	public void update(OccupationalHealthCertificateDTO certificateDTO) {
		OccupationalHealthCertificate defaultHealthCertficate = getHealthRepository().findById(certificateDTO.getId())
				.orElseThrow(() -> new RuntimeException("Health Certificate not found"));
		
		Employee employee = getEmployeeRepository().findById(certificateDTO.getEmployeeId())
				.orElseThrow(() -> new RuntimeException("Employee not found"));

		// Inserindo dados para entidade ASO já registrada para atualização
		// ------------------------------------------------//
		defaultHealthCertficate.setEmployee(employee);
		if(certificateDTO.getDate() != null) {
			defaultHealthCertficate.setDate(certificateDTO.getDate());
		}
		
		if(certificateDTO.isAccomplished()) {
			defaultHealthCertficate.setIsAccomplished(true);
		} else {
			defaultHealthCertficate.setIsAccomplished(false);
		}
		
		OccupationalHealthCertificateType type = getTypeRepository()
				.findById(certificateDTO.getOccupationHealthCertificateTypeId()).orElse(null);
		defaultHealthCertficate.setOccupationHealthCertificateType(type);

		if (certificateDTO.getOccupationHealthCertificateTypeId() == 1
				|| certificateDTO.getOccupationHealthCertificateTypeId() == 4) {

			if (certificateDTO.getNewCategoryId() != null) {
				Category newCategory = getCategoryRepository().findById(certificateDTO.getNewCategoryId())
						.orElseThrow(() -> new RuntimeException("Category not found"));
				defaultHealthCertficate.setNewCategory(newCategory);
			}

		} else {
			defaultHealthCertficate.setCurrentCategory(employee.getCategory());
		}

		getHealthRepository().save(defaultHealthCertficate);
	}

	public String delete(Long id) {
		OccupationalHealthCertificate healthCertificate = getHealthRepository().findById(id).orElse(null);

		if (healthCertificate != null) {
			getHealthRepository().deleteById(id);
			return "Health certificate deleted!";
		}
		return "Health certificate not found";
	}

	private OccupationalHealthCertificateRepository getHealthRepository() {
		return healthCertificateRepository;
	}

	private OccupationalHealthCertificateTypeRepository getTypeRepository() {
		return typeRepository;
	}

	private EmployeeRepository getEmployeeRepository() {
		return employeeRepository;
	}

	private CategoryRepository getCategoryRepository() {
		return categoryRepository;
	}

}
