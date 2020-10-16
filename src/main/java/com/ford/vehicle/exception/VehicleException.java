package com.ford.vehicle.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class VehicleException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String status = "fail";

	public VehicleException(String message) {
		super(message);

	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
