package com.example.rent_a_car.dao;

import com.example.rent_a_car.databaseRepository.AssignEntity;
import com.example.rent_a_car.entities.Assign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AssignsDAO extends JpaRepository<AssignEntity, Long> {
    @Query(value="SELECT * FROM assigns2 WHERE vehicle_id = :vehicleId", nativeQuery = true)
   Optional<AssignEntity> findByVehicle_id(@Param("vehicleId") String number);
}

//JPA: Java Persistance Api parte de Spring simplifica la interaccion conn bases de datos
//JPARepository: proporciona metodos CRUD