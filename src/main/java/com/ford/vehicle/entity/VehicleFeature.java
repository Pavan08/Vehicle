package com.ford.vehicle.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleFeature {

	@JsonProperty("Exterior")
	private List<String> exterior;
	
	@JsonProperty("Interior")
	private List<String> interior;

}
