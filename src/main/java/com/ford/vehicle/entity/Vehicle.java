package com.ford.vehicle.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Vehicle {
	
	@Id
	private Long vehicleId;
	
	private VehicleDetails vehicleDetails;
}
