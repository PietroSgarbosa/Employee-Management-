package com.employeemanagement.employeemanagement.exception;

public class CollaboratorDTOMissingException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public CollaboratorDTOMissingException() {
		super("COLLABORATOR CANNOT BE NULL");
	}
	
	public CollaboratorDTOMissingException(String errorMessage) {
		super(errorMessage);
	}

}
