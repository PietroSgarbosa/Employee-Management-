package com.employeemanagement.employeemanagement.exception;


public class TrainingDTOMissingException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TrainingDTOMissingException() {
		super("TRAINING CANNOT BE NULL");
	}
	
	public TrainingDTOMissingException(String erro) {
		super(erro);
	}

	
}
