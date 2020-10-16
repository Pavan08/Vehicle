package com.ford.vehicle.domain;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleSubmitResponse {

	private String status;
	private HttpStatus statusCode;
	private String message;

}
