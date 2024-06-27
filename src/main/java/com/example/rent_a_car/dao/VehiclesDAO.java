package com.example.rent_a_car.dao;

import com.example.rent_a_car.databaseRepository.VehicleEntity;
import com.example.rent_a_car.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiclesDAO extends JpaRepository<VehicleEntity, Long> {
}
