package com.ford.vehicle.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ford.vehicle.domain.ErrorReponse;
import com.ford.vehicle.exception.VehicleDataNotFoundException;
import com.ford.vehicle.exception.VehicleValidationException;
@ControllerAdvice
public class VehicleExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(VehicleDataNotFoundException.class)
	  public final ResponseEntity<ErrorReponse> handleDataNotFound(VehicleDataNotFoundException ex, WebRequest request) {
		ErrorReponse error = new ErrorReponse( "fail",ex.getMessage());
	    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	  }
	
	@ExceptionHandler(VehicleValidationException.class)
	  public final ResponseEntity<ErrorReponse> handleValidationException(VehicleValidationException ex, WebRequest request) {
		ErrorReponse error = new ErrorReponse( "fail",ex.getMessage());
	    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	  }
}
