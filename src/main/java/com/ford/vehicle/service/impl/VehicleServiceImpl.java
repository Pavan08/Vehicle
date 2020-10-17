package com.ford.vehicle.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ford.vehicle.domain.VehicleListDTO;
import com.ford.vehicle.domain.VehicleSubmitResponse;
import com.ford.vehicle.domain.VehiclesDTO;
import com.ford.vehicle.entity.Vehicle;
import com.ford.vehicle.exception.VehicleDataNotFoundException;
import com.ford.vehicle.repo.VehicleRepository;
import com.ford.vehicle.service.VehicleService;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	private VehicleRepository vehicleRepository;

	@Override
	public VehicleSubmitResponse submitVehicle(VehiclesDTO vehiclesDTO) {

		log.debug("VehicleServiceImpl :: submitVehicle start");
		StringBuilder buider = new StringBuilder();

		for (Vehicle vehicle : vehiclesDTO.getVehicles().getVehicle()) {
			vehicle = vehicleRepository.save(vehicle);
			buider.append(vehicle.getVehicleId() + ",");
		}
		log.debug("VehicleServiceImpl :: submitVehicle end");

		return new VehicleSubmitResponse("200", HttpStatus.OK,
				buider.toString() + " submitted to database successfully");
	}

	@Override
	public VehiclesDTO getVehicle() {
		log.debug("VehicleServiceImpl :: getVehicle start");
		List<Vehicle> vehicles = vehicleRepository.findAll();
		return createReponse(vehicles);
	}

	@Override
	public VehiclesDTO getVehicle(String model) {
		log.debug("VehicleServiceImpl :: getVehicle start model name " + model);
		List<Vehicle> vehicles = vehicleRepository.findVehicleByModel(model);
		return createReponse(vehicles);
	}

	//
	@Override
	public VehiclesDTO getVehiclePrice(String fromPrice, String toPrice) {
		log.debug("VehicleServiceImpl :: getVehiclePrice start from " + fromPrice + "to " + toPrice);

		List<Vehicle> vehicles = vehicleRepository.findVehicleByPriceBetween(Integer.parseInt(fromPrice),
				Integer.parseInt(toPrice));
		return createReponse(vehicles);
	}

	@Override
	public VehiclesDTO getVehicleByFeatures(String exterior, String interior) {
		log.debug("VehicleServiceImpl :: getVehicleByFeatures start exterior " + exterior + "interior " + interior);

		List<Vehicle> vehicles = vehicleRepository.findVehicleByFeature(exterior, interior);
		return createReponse(vehicles);
	}

	private VehiclesDTO createReponse(List<Vehicle> vehiclesList) {

		if (CollectionUtils.isEmpty(vehiclesList)) {
			throw new VehicleDataNotFoundException(" Vehicle Details not found");
		}

		VehiclesDTO dto = new VehiclesDTO();
		VehicleListDTO vehicles = new VehicleListDTO();
		vehicles.setVehicle(vehiclesList);
		dto.setVehicles(vehicles);
		return dto;

	}

}
