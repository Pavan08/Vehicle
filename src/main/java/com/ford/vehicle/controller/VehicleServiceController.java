package com.ford.vehicle.controller;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ford.vehicle.domain.VehiclesDTO;
import com.ford.vehicle.exception.VehicleValidationException;
import com.ford.vehicle.service.VehicleService;

@RestController
public class VehicleServiceController {

	@Autowired
	private VehicleService vehicleService;

	@PostMapping("/vehicleInfomation/submitVehicle")
	public ResponseEntity<?> submitVehicle(@RequestBody VehiclesDTO vehiclesDTO) {

		if (Objects.isNull(vehiclesDTO) || Objects.isNull(vehiclesDTO.getVehicles())
				|| CollectionUtils.isEmpty(vehiclesDTO.getVehicles().getVehicle())) {

			throw new VehicleValidationException("Invalid vehicles details");
		}

		return new ResponseEntity<>(vehicleService.submitVehicle(vehiclesDTO), HttpStatus.OK);
	}

	@GetMapping("/getVehicleInfomation")
	public ResponseEntity<VehiclesDTO> getVehicleInfomation() {

		return new ResponseEntity<>(vehicleService.getVehicle(), HttpStatus.OK);

	}

	@GetMapping("/getVehicleInfomation/{modelName}")
	public ResponseEntity<VehiclesDTO> getVehicleInfomation(@PathVariable String modelName) {
		return new ResponseEntity<>(vehicleService.getVehicle(modelName), HttpStatus.OK);

	}

	@GetMapping("/getVehiclePrice/{from}/{to}")
	public ResponseEntity<VehiclesDTO> getVehiclePrice(@PathVariable String from, @PathVariable String to) {
		return new ResponseEntity<>(vehicleService.getVehiclePrice(from, to), HttpStatus.OK);
	}

	@GetMapping("/getVehicleByFeatures/{exterior}/{interior}")
	public ResponseEntity<VehiclesDTO> getVehicleByFeatures(@PathVariable String exterior,
			@PathVariable String interior) {
		if (StringUtils.isBlank(exterior) || StringUtils.isBlank(interior) || exterior.length() < 3
				|| interior.length() < 3) {

			throw new VehicleValidationException(
					"Invalid exterior or interior input, Provide exterior and interior as minimum 3 letetrs");
		}

		return new ResponseEntity<>(vehicleService.getVehicleByFeatures(exterior, interior), HttpStatus.OK);
	}

}
