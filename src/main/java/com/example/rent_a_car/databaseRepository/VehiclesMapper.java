package com.example.rent_a_car.databaseRepository;

import com.example.rent_a_car.entities.Vehicle;

public class VehiclesMapper {

    public static Vehicle entityToDomain(VehicleEntity vehicleEntity){
        Vehicle vehicle = new Vehicle();
        vehicle.setId(vehicleEntity.getId());
        vehicle.setPlate(vehicleEntity.getPlate());
        vehicle.setModel(vehicleEntity.getModel());
        vehicle.setBrand(vehicleEntity.getBrand());
        vehicle.setYear(vehicleEntity.getYear());

        return vehicle;
    }

    public static VehicleEntity domainToentity(Vehicle vehicle){

        VehicleEntity vehicleEntity = new VehicleEntity();

        vehicleEntity.setId(vehicle.getId());
        vehicleEntity.setPlate(vehicle.getPlate());
        vehicleEntity.setModel(vehicle.getModel());
        vehicleEntity.setBrand(vehicle.getBrand());
        vehicleEntity.setYear(vehicle.getYear());


        return vehicleEntity;
    }
}
