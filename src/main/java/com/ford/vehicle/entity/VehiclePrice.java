package com.ford.vehicle.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehiclePrice {

	@JsonProperty("MSRP")
	private float mSRP;

	@JsonProperty("Savings")
	private float savings;

	private float finalPrice;
}
