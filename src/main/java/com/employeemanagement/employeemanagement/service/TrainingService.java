package com.employeemanagement.employeemanagement.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeemanagement.employeemanagement.dto.TrainingDTO;
import com.employeemanagement.employeemanagement.entity.Training;
import com.employeemanagement.employeemanagement.exception.TrainingException;
import com.employeemanagement.employeemanagement.repository.TrainingRepository;
import com.employeemanagement.employeemanagement.utils.TrainingMapper;


@Service
public class TrainingService {

	@Autowired
	private TrainingRepository trainingRepository;

	@Autowired
	private TrainingMapper trainingMapper;

	public Training getById(Long id) {
	    return getTrainingRepository().findById(id)
	            .orElseThrow(TrainingException::new);
	}

	public void create(Training training) {
		getTrainingRepository().save(training);
	}

	public List<TrainingDTO> getAll() {
		List<Training> trainingList = getTrainingRepository().findAll();
		return trainingList.stream().map(TrainingDTO::convertToDTO).collect(Collectors.toList());
	}

	public Training update(Long id, Training trainingCurrent) {

		Training training = getById(id);

		if (training != null) {

			if (trainingCurrent.getDescription() != null) {
				training.setDescription(trainingCurrent.getDescription());
			}

			return trainingRepository.save(training);
		} else {
			return null;
		}
	}

	public void delete(Long id) {
		trainingRepository.deleteById(id);
	}

	private TrainingRepository getTrainingRepository() {
		return trainingRepository;
	}

	private TrainingMapper getTrainingMapper() {
		return trainingMapper;
	}

}



