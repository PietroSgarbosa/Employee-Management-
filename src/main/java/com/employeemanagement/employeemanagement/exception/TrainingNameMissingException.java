package com.employeemanagement.employeemanagement.exception;

public class TrainingNameMissingException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public TrainingNameMissingException() {
		super("NAME CANNOT BE NULL");
	}
	
	public TrainingNameMissingException(String errorMessage) {
		super(errorMessage);
	}
}
