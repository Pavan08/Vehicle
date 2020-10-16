package com.ford.vehicle.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ford.vehicle.domain.VehicleListDTO;
import com.ford.vehicle.domain.VehicleSubmitResponse;
import com.ford.vehicle.domain.VehiclesDTO;
import com.ford.vehicle.entity.Vehicle;
import com.ford.vehicle.repo.VehicleRepository;
import com.ford.vehicle.service.VehicleService;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	private VehicleRepository vehicleRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	private String VehicleCollection = "vehicle";

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
		Query query = new Query();
		query.addCriteria(Criteria.where("vehicleDetails.model").is(model));
		List<Vehicle> vehicles = mongoTemplate.find(query, Vehicle.class, VehicleCollection);
		return createReponse(vehicles);
	}

	//
	@Override
	public VehiclesDTO getVehiclePrice(String from, String to) {
		log.debug("VehicleServiceImpl :: getVehiclePrice start from " + from + "to " + to);

		Query query = new Query();

		query.addCriteria(Criteria.where("vehicleDetails.vehiclePrice.finalPrice").exists(true).andOperator(
				Criteria.where("vehicleDetails.vehiclePrice.finalPrice").gt(Integer.parseInt(from)),
				Criteria.where("vehicleDetails.vehiclePrice.finalPrice").lt(Integer.parseInt(to))));
		List<Vehicle> vehicles = mongoTemplate.find(query, Vehicle.class, VehicleCollection);

		// List<Vehicle> vehicles =
		// vehicleRepository.findVehicleByPriceBetween(Integer.parseInt(from),
		// Integer.parseInt(to));
		return createReponse(vehicles);
	}

	@Override
	public VehiclesDTO getVehicleByFeatures(String exterior, String interior) {
		// TODO Auto-generated method stub
		return null;
	}

	private VehiclesDTO createReponse(List<Vehicle> vehiclesList) {
		VehiclesDTO dto = new VehiclesDTO();
		VehicleListDTO vehicles = new VehicleListDTO();
		vehicles.setVehicle(vehiclesList);
		dto.setVehicles(vehicles);
		return dto;

	}

}
