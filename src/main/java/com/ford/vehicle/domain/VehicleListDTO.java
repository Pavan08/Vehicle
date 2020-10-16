package com.ford.vehicle.domain;

import java.util.List;

import com.ford.vehicle.entity.Vehicle;

import lombok.Data;
@Data
public class VehicleListDTO {

	public List<Vehicle> vehicle;
}
