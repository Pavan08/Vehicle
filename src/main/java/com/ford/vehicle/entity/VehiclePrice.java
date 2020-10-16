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
	public String mSRP;

	@JsonProperty("Savings")
	public String savings;

	public String finalPrice;
}
