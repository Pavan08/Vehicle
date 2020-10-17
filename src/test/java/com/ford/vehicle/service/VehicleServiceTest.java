package com.ford.vehicle.service;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bson.json.JsonParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.ford.vehicle.domain.VehicleSubmitResponse;
import com.ford.vehicle.domain.VehiclesDTO;
import com.ford.vehicle.entity.Vehicle;
import com.ford.vehicle.exception.VehicleDataNotFoundException;
import com.ford.vehicle.repo.VehicleRepository;
import com.ford.vehicle.service.impl.VehicleServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class VehicleServiceTest {

	@Mock
	private VehicleRepository vehicleRepository;

	@InjectMocks
	private VehicleServiceImpl vehicleService;

	@Test
	public void getVehicleTest() throws JsonMappingException, JsonParseException, IOException {

		String vehicleJson = "{\"vehicleId\":\"105\",\"vehicleDetails\":{\"make\":\"Ford\",\"model\":\"Ranger\",\"modelYear\":\"2016\",\"bodyStyle\":\"Super Cab\",\"engine\":\"2.3L EcoBoost\",\"drivetype\":\"4WD\",\"color\":\"Oxford White\",\"MPG\":\"24\",\"vehicleFeature\":{\"Exterior\":[\"Daytime Running Lights\",\"Easy Fuel Capless Filler\",\"Fuel Tank - 18.0 Gallon\",\"Tow Hooks\"],\"Interior\":[\"110V Outlet\",\"Dual Sliding Sunvisors\",\"Dual Zone Auto Climate Ctl\",\"Overhead Console\"]},\"vehiclePrice\":[{\"MSRP\":\"35515.00\",\"Savings\":\"5000\",\"finalPrice\":\"30515.00\"}]}}";
		Vehicle vehicle = VehicleServiceUtil.mapFromJson(vehicleJson, Vehicle.class);
		List<Vehicle> vehicles = new ArrayList<>();
		vehicles.add(vehicle);
		Mockito.when(vehicleRepository.findAll()).thenReturn(vehicles);
		VehiclesDTO vehiclesDTO = vehicleService.getVehicle();
		assertTrue(vehiclesDTO.getVehicles().getVehicle().size() > 0);
	}

	@Test(expected = VehicleDataNotFoundException.class)
	public void getVehicleTest_DataNotFoundText() throws JsonMappingException, JsonParseException, IOException {
		List<Vehicle> vehicles = new ArrayList<>();
		Mockito.when(vehicleRepository.findAll()).thenReturn(vehicles);
		vehicleService.getVehicle();
	}

	@Test
	public void submitVehicleTest() throws JsonMappingException, JsonParseException, IOException {

		String vehicleJson = "{\"vehicles\":{\"vehicle\":[{\"vehicleId\":\"101\",\"vehicleDetails\":{\"make\":\"Ford\",\"model\":\"ecoSport\",\"modelYear\":\"2020\",\"bodyStyle\":\"4D Sport Utility\",\"engine\":\"1.0L EcoBoost\",\"drivetype\":\"FWD\",\"color\":\"shadow black\",\"MPG\":\"27\",\"vehicleFeature\":{\"Exterior\":[\"Acoustic-Laminate Windshld\",\"Active Grille Shutters\",\"Windshield Wiper De-Icer\",\"Privacy Glass - Rear Doors\"],\"Interior\":[\"Illuminated Entry System\",\"Powerpoints - 12V\",\"Power Driver Seat - 6-Way\",\"Unique Clth/Htd Frt Seats\"]},\"vehiclePrice\":[{\"MSRP\":\"25000.00\",\"Savings\":\"5000\",\"finalPrice\":\"20000.00\"}]}},{\"vehicleId\":\"102\",\"vehicleDetails\":{\"make\":\"Ford\",\"model\":\"Edge\",\"modelYear\":\"2019\",\"bodyStyle\":\"4D Sport Utility\",\"engine\":\"Twin-Scroll 2.0L EcoBoost\",\"drivetype\":\"AWD\",\"color\":\"Agate Black\",\"MPG\":\"28\",\"vehicleFeature\":{\"Exterior\":[\"Beltline Molding - Black\",\"Door Handles - Body Color\",\"Grille - Chrome\",\"Taillamps-Led\"],\"Interior\":[\"60/40 Split Fold Rear Seat\",\"Cruise Control\",\"Dual Illum Vis Vanity Mirr\",\"Rotary Gear Shift Dial\"]},\"vehiclePrice\":[{\"MSRP\":\"30000.00\",\"Savings\":\"2000\",\"finalPrice\":\"28000.00\"}]}},{\"vehicleId\":\"103\",\"vehicleDetails\":{\"make\":\"Ford\",\"model\":\"F-150\",\"modelYear\":\"2020\",\"bodyStyle\":\"4D SuperCrew\",\"engine\":\"V6 PFDI\",\"drivetype\":\"4WD\",\"color\":\"Blue Jeans Metallic\",\"MPG\":\"23\",\"vehicleFeature\":{\"Exterior\":[\"Headlamps - Autolamp\",\"Locking Removable Tailgate\",\"Manual Fold Power Mirrors\",\"Headlamps - Auto High Beam\"],\"Interior\":[\"1Touch Up/Down Dr/Pass Win\",\"60/40 Fold-Up Rear Bench Seat\",\"Cruise Control\",\"Illuminated Entry\"]},\"vehiclePrice\":[{\"MSRP\":\"40925.00\",\"Savings\":\"4678.00\",\"finalPrice\":\"36247.00\"}]}},{\"vehicleId\":\"104\",\"vehicleDetails\":{\"make\":\"Ford\",\"model\":\"mustang\",\"modelYear\":\"2017\",\"bodyStyle\":\"4D Sport Utility\",\"engine\":\"V8\",\"drivetype\":\"RWD\",\"color\":\"Blue Metallic\",\"MPG\":\"32\",\"vehicleFeature\":{\"Exterior\":[\"Dual Exhaust System\",\"Easy Fuel Capless Filler\",\"Headlamps - Autolamp\",\"Headlamps- Led With Signature Lighting\"],\"Interior\":[\"Autodim Rearview Mirror\",\"Center Console W/Armrest\",\"Floor Mats - Front\",\"Smart Charging Usb Port(2)\"]},\"vehiclePrice\":[{\"MSRP\":\"33645.70\",\"Savings\":\"4988.20\",\"finalPrice\":\"28657.50\"}]}},{\"vehicleId\":\"105\",\"vehicleDetails\":{\"make\":\"Ford\",\"model\":\"Ranger\",\"modelYear\":\"2016\",\"bodyStyle\":\"Super Cab\",\"engine\":\"2.3L EcoBoost\",\"drivetype\":\"4WD\",\"color\":\"Oxford White\",\"MPG\":\"24\",\"vehicleFeature\":{\"Exterior\":[\"Daytime Running Lights\",\"Easy Fuel Capless Filler\",\"Fuel Tank - 18.0 Gallon\",\"Tow Hooks\"],\"Interior\":[\"110V Outlet\",\"Dual Sliding Sunvisors\",\"Dual Zone Auto Climate Ctl\",\"Overhead Console\"]},\"vehiclePrice\":[{\"MSRP\":\"35515.00\",\"Savings\":\"5000\",\"finalPrice\":\"30515.00\"}]}}]}}";
		VehiclesDTO vehiclesDTO = VehicleServiceUtil.mapFromJson(vehicleJson, VehiclesDTO.class);

		VehicleSubmitResponse vehicleSubmitResponse = vehicleService.submitVehicle(vehiclesDTO);
		assertTrue(vehicleSubmitResponse.getMessage() != null);
	}
	
	@Test
	public void getVehicleByModelTest() throws JsonMappingException, JsonParseException, IOException {

		String vehicleJson = "{\"vehicleId\":\"105\",\"vehicleDetails\":{\"make\":\"Ford\",\"model\":\"Ranger\",\"modelYear\":\"2016\",\"bodyStyle\":\"Super Cab\",\"engine\":\"2.3L EcoBoost\",\"drivetype\":\"4WD\",\"color\":\"Oxford White\",\"MPG\":\"24\",\"vehicleFeature\":{\"Exterior\":[\"Daytime Running Lights\",\"Easy Fuel Capless Filler\",\"Fuel Tank - 18.0 Gallon\",\"Tow Hooks\"],\"Interior\":[\"110V Outlet\",\"Dual Sliding Sunvisors\",\"Dual Zone Auto Climate Ctl\",\"Overhead Console\"]},\"vehiclePrice\":[{\"MSRP\":\"35515.00\",\"Savings\":\"5000\",\"finalPrice\":\"30515.00\"}]}}";
		Vehicle vehicle = VehicleServiceUtil.mapFromJson(vehicleJson, Vehicle.class);
		List<Vehicle> vehicles = new ArrayList<>();
		vehicles.add(vehicle);
		Mockito.when(vehicleRepository.findVehicleByModel(Mockito.anyString())).thenReturn(vehicles);
		VehiclesDTO vehiclesDTO = vehicleService.getVehicle("Ranger");
		assertTrue(vehiclesDTO.getVehicles().getVehicle().size() > 0);
	}
	@Test
	public void getVehiclePriceTest() throws JsonMappingException, JsonParseException, IOException {

		String vehicleJson = "{\"vehicleId\":\"105\",\"vehicleDetails\":{\"make\":\"Ford\",\"model\":\"Ranger\",\"modelYear\":\"2016\",\"bodyStyle\":\"Super Cab\",\"engine\":\"2.3L EcoBoost\",\"drivetype\":\"4WD\",\"color\":\"Oxford White\",\"MPG\":\"24\",\"vehicleFeature\":{\"Exterior\":[\"Daytime Running Lights\",\"Easy Fuel Capless Filler\",\"Fuel Tank - 18.0 Gallon\",\"Tow Hooks\"],\"Interior\":[\"110V Outlet\",\"Dual Sliding Sunvisors\",\"Dual Zone Auto Climate Ctl\",\"Overhead Console\"]},\"vehiclePrice\":[{\"MSRP\":\"35515.00\",\"Savings\":\"5000\",\"finalPrice\":\"30515.00\"}]}}";
		Vehicle vehicle = VehicleServiceUtil.mapFromJson(vehicleJson, Vehicle.class);
		List<Vehicle> vehicles = new ArrayList<>();
		vehicles.add(vehicle);
		Mockito.when(vehicleRepository.findVehicleByPriceBetween(Mockito.anyInt(),Mockito.anyInt())).thenReturn(vehicles);
		VehiclesDTO vehiclesDTO = vehicleService.getVehiclePrice("1000","20000");
		assertTrue(vehiclesDTO.getVehicles().getVehicle().size() > 0);
	}
	
	@Test
	public void getVehicleByFeaturesTest() throws JsonMappingException, JsonParseException, IOException {

		String vehicleJson = "{\"vehicleId\":\"105\",\"vehicleDetails\":{\"make\":\"Ford\",\"model\":\"Ranger\",\"modelYear\":\"2016\",\"bodyStyle\":\"Super Cab\",\"engine\":\"2.3L EcoBoost\",\"drivetype\":\"4WD\",\"color\":\"Oxford White\",\"MPG\":\"24\",\"vehicleFeature\":{\"Exterior\":[\"Daytime Running Lights\",\"Easy Fuel Capless Filler\",\"Fuel Tank - 18.0 Gallon\",\"Tow Hooks\"],\"Interior\":[\"110V Outlet\",\"Dual Sliding Sunvisors\",\"Dual Zone Auto Climate Ctl\",\"Overhead Console\"]},\"vehiclePrice\":[{\"MSRP\":\"35515.00\",\"Savings\":\"5000\",\"finalPrice\":\"30515.00\"}]}}";
		Vehicle vehicle = VehicleServiceUtil.mapFromJson(vehicleJson, Vehicle.class);
		List<Vehicle> vehicles = new ArrayList<>();
		vehicles.add(vehicle);
		Mockito.when(vehicleRepository.findVehicleByFeature(Mockito.anyString(),Mockito.anyString())).thenReturn(vehicles);
		VehiclesDTO vehiclesDTO = vehicleService.getVehicleByFeatures("Light","Sunvisors");
		assertTrue(vehiclesDTO.getVehicles().getVehicle().size() > 0);
	}
}
