package com.employeemanagement.employeemanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeemanagement.employeemanagement.dto.TrainingDTO;
import com.employeemanagement.employeemanagement.entity.Training;
import com.employeemanagement.employeemanagement.exception.TrainingDTOMissingException;
import com.employeemanagement.employeemanagement.exception.TrainingTitleMissingException;
import com.employeemanagement.employeemanagement.repository.TrainingRepository;
import com.employeemanagement.employeemanagement.utils.TrainingMapper;

@Service
public class TrainingService {

	@Autowired
	private TrainingRepository trainingRepository;
	
	@Autowired
	private TrainingMapper trainingMapper;
	
	
	public Training getById(Long id) {
			return getTrainingRepository().findById(id).orElse(null);
	}
	
	public void create (TrainingDTO trainingDTO) {
		if(trainingDTO != null) {
			if(trainingDTO.getTitle() == null) {
				throw new TrainingTitleMissingException();
			}else {
				Training trainingEntity = getTrainingMapper().convertToEntity(trainingDTO);
				getTrainingRepository().save(trainingEntity);
			}
		}else {
			throw new TrainingDTOMissingException();
		}
	}
	
	
	public String update(TrainingDTO trainingDTO) {
		Training defaultTraining = getById(trainingDTO.getId());
		String responseMessage = "Training of ID " + trainingDTO.getId() + " not found";

		if(defaultTraining != null) {
			defaultTraining.setTitle(trainingDTO.getTitle());
			defaultTraining.setDescription(trainingDTO.getDescription());
			create(TrainingDTO.convertToDTO(defaultTraining));
			responseMessage = "Training of ID " + trainingDTO.getId() + " updated successfully!";
			return responseMessage;
		}
		return responseMessage;
	}
	
	public String delete(Long id) {
		Training training = getById(id);
		
		if(training == null) {
			return "The training ID " + id + " doesn't exist";
		} else {
			getTrainingRepository().deleteById(id);
			return "Training of ID " + id + " has been deleted!";
		}
		
	}
	
	
	private TrainingMapper getTrainingMapper() {
		return trainingMapper;
	}
	private TrainingRepository getTrainingRepository() {
		return trainingRepository;
	}
}
