package com.employeemanagement.employeemanagement.exception;

public class EmployeeDTOMissingException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public EmployeeDTOMissingException() {
		super("EMPLOYEE CANNOT BE NULL");
	}
	
	public EmployeeDTOMissingException(String errorMessage) {
		super(errorMessage);
	}

}
