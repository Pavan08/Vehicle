package com.ford.vehicle.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ford.vehicle.domain.VehicleSubmitResponse;
import com.ford.vehicle.domain.VehiclesDTO;
import com.ford.vehicle.service.VehicleService;
import com.ford.vehicle.service.VehicleServiceUtil;

@RunWith(SpringRunner.class)
@WebMvcTest(value = VehicleServiceController.class)
public class VehicleServiceControllertTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private VehicleService vehicleService;

	@Test
	public void submitVehicleTest() throws Exception {
		String uri = "/vehicleInfomation/submitVehicle";
		String vehicleJson = "{\"vehicles\":{\"vehicle\":[{\"vehicleId\":\"101\",\"vehicleDetails\":{\"make\":\"Ford\",\"model\":\"ecoSport\",\"modelYear\":\"2020\",\"bodyStyle\":\"4D Sport Utility\",\"engine\":\"1.0L EcoBoost\",\"drivetype\":\"FWD\",\"color\":\"shadow black\",\"MPG\":\"27\",\"vehicleFeature\":{\"Exterior\":[\"Acoustic-Laminate Windshld\",\"Active Grille Shutters\",\"Windshield Wiper De-Icer\",\"Privacy Glass - Rear Doors\"],\"Interior\":[\"Illuminated Entry System\",\"Powerpoints - 12V\",\"Power Driver Seat - 6-Way\",\"Unique Clth/Htd Frt Seats\"]},\"vehiclePrice\":[{\"MSRP\":\"25000.00\",\"Savings\":\"5000\",\"finalPrice\":\"20000.00\"}]}},{\"vehicleId\":\"102\",\"vehicleDetails\":{\"make\":\"Ford\",\"model\":\"Edge\",\"modelYear\":\"2019\",\"bodyStyle\":\"4D Sport Utility\",\"engine\":\"Twin-Scroll 2.0L EcoBoost\",\"drivetype\":\"AWD\",\"color\":\"Agate Black\",\"MPG\":\"28\",\"vehicleFeature\":{\"Exterior\":[\"Beltline Molding - Black\",\"Door Handles - Body Color\",\"Grille - Chrome\",\"Taillamps-Led\"],\"Interior\":[\"60/40 Split Fold Rear Seat\",\"Cruise Control\",\"Dual Illum Vis Vanity Mirr\",\"Rotary Gear Shift Dial\"]},\"vehiclePrice\":[{\"MSRP\":\"30000.00\",\"Savings\":\"2000\",\"finalPrice\":\"28000.00\"}]}},{\"vehicleId\":\"103\",\"vehicleDetails\":{\"make\":\"Ford\",\"model\":\"F-150\",\"modelYear\":\"2020\",\"bodyStyle\":\"4D SuperCrew\",\"engine\":\"V6 PFDI\",\"drivetype\":\"4WD\",\"color\":\"Blue Jeans Metallic\",\"MPG\":\"23\",\"vehicleFeature\":{\"Exterior\":[\"Headlamps - Autolamp\",\"Locking Removable Tailgate\",\"Manual Fold Power Mirrors\",\"Headlamps - Auto High Beam\"],\"Interior\":[\"1Touch Up/Down Dr/Pass Win\",\"60/40 Fold-Up Rear Bench Seat\",\"Cruise Control\",\"Illuminated Entry\"]},\"vehiclePrice\":[{\"MSRP\":\"40925.00\",\"Savings\":\"4678.00\",\"finalPrice\":\"36247.00\"}]}},{\"vehicleId\":\"104\",\"vehicleDetails\":{\"make\":\"Ford\",\"model\":\"mustang\",\"modelYear\":\"2017\",\"bodyStyle\":\"4D Sport Utility\",\"engine\":\"V8\",\"drivetype\":\"RWD\",\"color\":\"Blue Metallic\",\"MPG\":\"32\",\"vehicleFeature\":{\"Exterior\":[\"Dual Exhaust System\",\"Easy Fuel Capless Filler\",\"Headlamps - Autolamp\",\"Headlamps- Led With Signature Lighting\"],\"Interior\":[\"Autodim Rearview Mirror\",\"Center Console W/Armrest\",\"Floor Mats - Front\",\"Smart Charging Usb Port(2)\"]},\"vehiclePrice\":[{\"MSRP\":\"33645.70\",\"Savings\":\"4988.20\",\"finalPrice\":\"28657.50\"}]}},{\"vehicleId\":\"105\",\"vehicleDetails\":{\"make\":\"Ford\",\"model\":\"Ranger\",\"modelYear\":\"2016\",\"bodyStyle\":\"Super Cab\",\"engine\":\"2.3L EcoBoost\",\"drivetype\":\"4WD\",\"color\":\"Oxford White\",\"MPG\":\"24\",\"vehicleFeature\":{\"Exterior\":[\"Daytime Running Lights\",\"Easy Fuel Capless Filler\",\"Fuel Tank - 18.0 Gallon\",\"Tow Hooks\"],\"Interior\":[\"110V Outlet\",\"Dual Sliding Sunvisors\",\"Dual Zone Auto Climate Ctl\",\"Overhead Console\"]},\"vehiclePrice\":[{\"MSRP\":\"35515.00\",\"Savings\":\"5000\",\"finalPrice\":\"30515.00\"}]}}]}}";
		String reponseJson = "{\"status\":\"200\",\"statusCode\":\"OK\",\"message\":\"101,102,103,104,105, submitted to database successfully\"}";

		VehicleSubmitResponse vehiclesDTOReponse = VehicleServiceUtil.mapFromJson(reponseJson,
				VehicleSubmitResponse.class);
		Mockito.when(vehicleService.submitVehicle(Mockito.any())).thenReturn(vehiclesDTOReponse);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(uri).accept(MediaType.APPLICATION_JSON)
				.content(vehicleJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		int status = result.getResponse().getStatus();
		assertEquals(200, status);
		String content = result.getResponse().getContentAsString();
		VehicleSubmitResponse vehicleSubmitResponse = VehicleServiceUtil.mapFromJson(content,
				VehicleSubmitResponse.class);
		assertTrue(
				vehicleSubmitResponse.getMessage().equals("101,102,103,104,105, submitted to database successfully"));
	}

	@Test
	public void getVehicleInfomationTest() throws Exception {
		String uri = "/getVehicleInfomation";
		String vehicleJson = "{\"vehicles\":{\"vehicle\":[{\"vehicleId\":\"101\",\"vehicleDetails\":{\"make\":\"Ford\",\"model\":\"ecoSport\",\"modelYear\":\"2020\",\"bodyStyle\":\"4D Sport Utility\",\"engine\":\"1.0L EcoBoost\",\"drivetype\":\"FWD\",\"color\":\"shadow black\",\"MPG\":\"27\",\"vehicleFeature\":{\"Exterior\":[\"Acoustic-Laminate Windshld\",\"Active Grille Shutters\",\"Windshield Wiper De-Icer\",\"Privacy Glass - Rear Doors\"],\"Interior\":[\"Illuminated Entry System\",\"Powerpoints - 12V\",\"Power Driver Seat - 6-Way\",\"Unique Clth/Htd Frt Seats\"]},\"vehiclePrice\":[{\"MSRP\":\"25000.00\",\"Savings\":\"5000\",\"finalPrice\":\"20000.00\"}]}},{\"vehicleId\":\"102\",\"vehicleDetails\":{\"make\":\"Ford\",\"model\":\"Edge\",\"modelYear\":\"2019\",\"bodyStyle\":\"4D Sport Utility\",\"engine\":\"Twin-Scroll 2.0L EcoBoost\",\"drivetype\":\"AWD\",\"color\":\"Agate Black\",\"MPG\":\"28\",\"vehicleFeature\":{\"Exterior\":[\"Beltline Molding - Black\",\"Door Handles - Body Color\",\"Grille - Chrome\",\"Taillamps-Led\"],\"Interior\":[\"60/40 Split Fold Rear Seat\",\"Cruise Control\",\"Dual Illum Vis Vanity Mirr\",\"Rotary Gear Shift Dial\"]},\"vehiclePrice\":[{\"MSRP\":\"30000.00\",\"Savings\":\"2000\",\"finalPrice\":\"28000.00\"}]}},{\"vehicleId\":\"103\",\"vehicleDetails\":{\"make\":\"Ford\",\"model\":\"F-150\",\"modelYear\":\"2020\",\"bodyStyle\":\"4D SuperCrew\",\"engine\":\"V6 PFDI\",\"drivetype\":\"4WD\",\"color\":\"Blue Jeans Metallic\",\"MPG\":\"23\",\"vehicleFeature\":{\"Exterior\":[\"Headlamps - Autolamp\",\"Locking Removable Tailgate\",\"Manual Fold Power Mirrors\",\"Headlamps - Auto High Beam\"],\"Interior\":[\"1Touch Up/Down Dr/Pass Win\",\"60/40 Fold-Up Rear Bench Seat\",\"Cruise Control\",\"Illuminated Entry\"]},\"vehiclePrice\":[{\"MSRP\":\"40925.00\",\"Savings\":\"4678.00\",\"finalPrice\":\"36247.00\"}]}},{\"vehicleId\":\"104\",\"vehicleDetails\":{\"make\":\"Ford\",\"model\":\"mustang\",\"modelYear\":\"2017\",\"bodyStyle\":\"4D Sport Utility\",\"engine\":\"V8\",\"drivetype\":\"RWD\",\"color\":\"Blue Metallic\",\"MPG\":\"32\",\"vehicleFeature\":{\"Exterior\":[\"Dual Exhaust System\",\"Easy Fuel Capless Filler\",\"Headlamps - Autolamp\",\"Headlamps- Led With Signature Lighting\"],\"Interior\":[\"Autodim Rearview Mirror\",\"Center Console W/Armrest\",\"Floor Mats - Front\",\"Smart Charging Usb Port(2)\"]},\"vehiclePrice\":[{\"MSRP\":\"33645.70\",\"Savings\":\"4988.20\",\"finalPrice\":\"28657.50\"}]}},{\"vehicleId\":\"105\",\"vehicleDetails\":{\"make\":\"Ford\",\"model\":\"Ranger\",\"modelYear\":\"2016\",\"bodyStyle\":\"Super Cab\",\"engine\":\"2.3L EcoBoost\",\"drivetype\":\"4WD\",\"color\":\"Oxford White\",\"MPG\":\"24\",\"vehicleFeature\":{\"Exterior\":[\"Daytime Running Lights\",\"Easy Fuel Capless Filler\",\"Fuel Tank - 18.0 Gallon\",\"Tow Hooks\"],\"Interior\":[\"110V Outlet\",\"Dual Sliding Sunvisors\",\"Dual Zone Auto Climate Ctl\",\"Overhead Console\"]},\"vehiclePrice\":[{\"MSRP\":\"35515.00\",\"Savings\":\"5000\",\"finalPrice\":\"30515.00\"}]}}]}}";
		VehiclesDTO vehiclesDTOReponse = VehicleServiceUtil.mapFromJson(vehicleJson, VehiclesDTO.class);
		Mockito.when(vehicleService.getVehicle()).thenReturn(vehiclesDTOReponse);
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		VehiclesDTO vehiclesDTO = VehicleServiceUtil.mapFromJson(content, VehiclesDTO.class);
		assertTrue(vehiclesDTO.getVehicles().getVehicle().size() > 0);
	}

	@Test
	public void getVehicleInfomationByModelTest() throws Exception {
		String uri = "/getVehicleInfomation/Ranger";
		String vehicleJson = "{\"vehicles\":{\"vehicle\":[{\"vehicleId\":\"105\",\"vehicleDetails\":{\"make\":\"Ford\",\"model\":\"Ranger\",\"modelYear\":\"2016\",\"bodyStyle\":\"Super Cab\",\"engine\":\"2.3L EcoBoost\",\"drivetype\":\"4WD\",\"color\":\"Oxford White\",\"MPG\":\"24\",\"vehicleFeature\":{\"Exterior\":[\"Daytime Running Lights\",\"Easy Fuel Capless Filler\",\"Fuel Tank - 18.0 Gallon\",\"Tow Hooks\"],\"Interior\":[\"110V Outlet\",\"Dual Sliding Sunvisors\",\"Dual Zone Auto Climate Ctl\",\"Overhead Console\"]},\"vehiclePrice\":[{\"MSRP\":\"35515.00\",\"Savings\":\"5000\",\"finalPrice\":\"30515.00\"}]}}]}}";
		VehiclesDTO vehiclesDTOReponse = VehicleServiceUtil.mapFromJson(vehicleJson, VehiclesDTO.class);
		Mockito.when(vehicleService.getVehicle(Mockito.anyString())).thenReturn(vehiclesDTOReponse);
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		VehiclesDTO vehiclesDTO = VehicleServiceUtil.mapFromJson(content, VehiclesDTO.class);
		assertTrue(vehiclesDTO.getVehicles().getVehicle().size() > 0);
		assertTrue(vehiclesDTO.getVehicles().getVehicle().get(0).getVehicleDetails().getModel().equals("Ranger"));
	}

	@Test
	public void getVehiclePriceTest() throws Exception {
		String uri = "/getVehiclePrice/10000/200000";
		String vehicleJson = "{\"vehicles\":{\"vehicle\":[{\"vehicleId\":\"105\",\"vehicleDetails\":{\"make\":\"Ford\",\"model\":\"Ranger\",\"modelYear\":\"2016\",\"bodyStyle\":\"Super Cab\",\"engine\":\"2.3L EcoBoost\",\"drivetype\":\"4WD\",\"color\":\"Oxford White\",\"MPG\":\"24\",\"vehicleFeature\":{\"Exterior\":[\"Daytime Running Lights\",\"Easy Fuel Capless Filler\",\"Fuel Tank - 18.0 Gallon\",\"Tow Hooks\"],\"Interior\":[\"110V Outlet\",\"Dual Sliding Sunvisors\",\"Dual Zone Auto Climate Ctl\",\"Overhead Console\"]},\"vehiclePrice\":[{\"MSRP\":\"35515.00\",\"Savings\":\"5000\",\"finalPrice\":\"30515.00\"}]}}]}}";
		VehiclesDTO vehiclesDTOReponse = VehicleServiceUtil.mapFromJson(vehicleJson, VehiclesDTO.class);
		Mockito.when(vehicleService.getVehiclePrice(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(vehiclesDTOReponse);
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		VehiclesDTO vehiclesDTO = VehicleServiceUtil.mapFromJson(content, VehiclesDTO.class);
		assertTrue(vehiclesDTO.getVehicles().getVehicle().size() > 0);
	}

	@Test
	public void getVehicleByFeaturesTest() throws Exception {
		String uri = "/getVehicleByFeatures/Light/Sunvisors";
		String vehicleJson = "{\"vehicles\":{\"vehicle\":[{\"vehicleId\":104,\"vehicleDetails\":{\"make\":\"Ford\",\"model\":\"mustang\",\"modelYear\":2017,\"bodyStyle\":\"4D Sport Utility\",\"engine\":\"V8\",\"drivetype\":\"RWD\",\"color\":\"Blue Metallic\",\"vehicleFeature\":{\"Exterior\":[\"Dual Exhaust System\",\"Easy Fuel Capless Filler\",\"Headlamps - Autolamp\",\"Headlamps- Led With Signature Lighting\"],\"Interior\":[\"Autodim Rearview Mirror\",\"Center Console W/Armrest\",\"Floor Mats - Front\",\"Smart Charging Usb Port(2)\"]},\"vehiclePrice\":[{\"finalPrice\":28657.5,\"MSRP\":33645.7,\"Savings\":4988.2}],\"MPG\":\"32\"}},{\"vehicleId\":105,\"vehicleDetails\":{\"make\":\"Ford\",\"model\":\"Ranger\",\"modelYear\":2016,\"bodyStyle\":\"Super Cab\",\"engine\":\"2.3L EcoBoost\",\"drivetype\":\"4WD\",\"color\":\"Oxford White\",\"vehicleFeature\":{\"Exterior\":[\"Daytime Running Lights\",\"Easy Fuel Capless Filler\",\"Fuel Tank - 18.0 Gallon\",\"Tow Hooks\"],\"Interior\":[\"110V Outlet\",\"Dual Sliding Sunvisors\",\"Dual Zone Auto Climate Ctl\",\"Overhead Console\"]},\"vehiclePrice\":[{\"finalPrice\":30515,\"MSRP\":35515,\"Savings\":5000}],\"MPG\":\"24\"}}]}}";
		VehiclesDTO vehiclesDTOReponse = VehicleServiceUtil.mapFromJson(vehicleJson, VehiclesDTO.class);
		Mockito.when(vehicleService.getVehicleByFeatures(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(vehiclesDTOReponse);
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		VehiclesDTO vehiclesDTO = VehicleServiceUtil.mapFromJson(content, VehiclesDTO.class);
		assertTrue(vehiclesDTO.getVehicles().getVehicle().size() > 0);
	}

}
