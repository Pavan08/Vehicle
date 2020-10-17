package com.ford.vehicle.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.ford.vehicle.entity.Vehicle;

@Repository
public interface VehicleRepository extends MongoRepository<Vehicle, String> {
	
	@Query("{ 'vehicleDetails.vehiclePrice.finalPrice' : { $gt: ?0, $lt: ?1 } }")
	List<Vehicle> findVehicleByPriceBetween(int from, int to);

	@Query("{ 'vehicleDetails.model' :?0}")
	List<Vehicle> findVehicleByModel(String model);
	
	
	@Query(value = "{ $or: [ { 'vehicleDetails.vehicleFeature.exterior' : {$regex:?0} }, { 'vehicleDetails.vehicleFeature.interior' : {$regex:?0} } ] }")
	List<Vehicle> findVehicleByFeature(String exterior, String interior);
}
