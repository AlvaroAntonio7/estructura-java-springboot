package com.example.rent_a_car.controllers;


import com.example.rent_a_car.dao.VehiclesDAO;
import com.example.rent_a_car.entities.Vehicle;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vehicles")
@Tag(name = "Documentacion del endpoint vehicles")
public class VehiclesController {

    @Autowired
    private VehiclesDAO vehiclesDAO;

    @GetMapping
    @Operation(summary = "get all vehicles form the bata base")
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        List<Vehicle> response = vehiclesDAO.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "get just one vehicle depending of the id on url")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable("id") Long id){
        Optional<Vehicle> vehicleResponse = vehiclesDAO.findById(id);
        //Otra forma
        //return vehicleResponse.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
        if(vehicleResponse.isPresent()){
            return ResponseEntity.ok(vehicleResponse.get());
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    @Operation(summary = "Save a new vehicle on dat base")
    public ResponseEntity<Vehicle> saveNewVehicle(@RequestBody Vehicle vehicleSave){
        Vehicle vehicle = vehiclesDAO.save(vehicleSave);
        return ResponseEntity.ok(vehicle);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a vehicle with the id passed on url")
    public ResponseEntity<String> deleteVehicle(@PathVariable("id") Long id){
        vehiclesDAO.deleteById(id);
        return ResponseEntity.ok("Dato Borrado");
    }

    @PutMapping
    @Operation(summary = "upadte  a vehicle from database")
    public ResponseEntity<Vehicle> updateVehicle(@RequestBody Vehicle updatedVehicle){
        Optional<Vehicle> optionalVehicle= vehiclesDAO.findById(updatedVehicle.getId());

        if(optionalVehicle.isPresent()){
            Vehicle saveVehicle = optionalVehicle.get();
            saveVehicle.setBrand(updatedVehicle.getBrand());
            saveVehicle.setModel(updatedVehicle.getModel());
            saveVehicle.setYear(updatedVehicle.getYear());
            saveVehicle.setPlate(updatedVehicle.getPlate());

            vehiclesDAO.save(saveVehicle);
            return ResponseEntity.ok(saveVehicle);
        }else{ return ResponseEntity.notFound().build();}
    }

}


