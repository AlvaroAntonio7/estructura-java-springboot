package com.example.rent_a_car.port;



import com.example.rent_a_car.entities.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleCrud {
    public List<Vehicle> findAllVehicles();

    public Optional<Vehicle> findVehicleById(Long id);

    public Vehicle updateVehicle(Vehicle vehicle);

    public void deleteVehicle(Long id);

    public Vehicle insertVehicle(Vehicle vehicle);
}
