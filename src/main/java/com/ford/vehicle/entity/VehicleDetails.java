package com.ford.vehicle.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDetails {
	private String make;
	private String model;
	private Long modelYear;
	private String bodyStyle;
	private String engine;
	private String drivetype;
	private String color;
	@JsonProperty("MPG")
	private String mPG;
	private VehicleFeature vehicleFeature;
	private List<VehiclePrice> vehiclePrice;
}
