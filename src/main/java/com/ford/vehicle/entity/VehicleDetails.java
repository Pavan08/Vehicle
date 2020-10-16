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
	public String make;
	public String model;
	public String modelYear;
	public String bodyStyle;
	public String engine;
	public String drivetype;
	public String color;
	@JsonProperty("MPG")
	public String mPG;
	public VehicleFeature vehicleFeature;
	public List<VehiclePrice> vehiclePrice;
}
