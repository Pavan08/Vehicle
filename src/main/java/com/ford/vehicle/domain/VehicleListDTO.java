package com.ford.vehicle.domain;

import java.util.List;

import com.ford.vehicle.entity.Vehicle;

import lombok.Data;
@Data
public class VehicleListDTO {

	private List<Vehicle> vehicle;
}
