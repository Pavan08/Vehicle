package com.ford.vehicle.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorReponse {
	private String status;
	private String message;
}
