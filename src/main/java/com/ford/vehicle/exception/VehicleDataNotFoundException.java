package com.ford.vehicle.exception;

public class VehicleDataNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public VehicleDataNotFoundException(String message) {
		super(message);
	}

}
