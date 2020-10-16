package com.ford.vehicle.service;

import com.ford.vehicle.domain.VehicleSubmitResponse;
import com.ford.vehicle.domain.VehiclesDTO;

public interface VehicleService {

	VehicleSubmitResponse submitVehicle(VehiclesDTO vehiclesDTO);

	VehiclesDTO getVehicle(String model);
	
	VehiclesDTO getVehiclePrice(String from ,String to);
	
	VehiclesDTO getVehicleByFeatures(String exterior ,String interior);

	VehiclesDTO getVehicle();
}
