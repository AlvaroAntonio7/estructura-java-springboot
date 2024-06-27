package com.example.rent_a_car.controllers;


import com.example.rent_a_car.entities.Vehicle;
import com.example.rent_a_car.port.VehicleCrud;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vehicles")
@Tag(name = "Documentacion del endpoint vehicles")
public class VehiclesController {

    //@Autowired
    //private VehiclesDAO vehiclesDAO;
    public final VehicleCrud vehicleCrud;

    public VehiclesController(VehicleCrud vehicleCrud){
        this.vehicleCrud = vehicleCrud;
    }

    @CrossOrigin(origins = "*") // para solo permitie uno @CrossOrigin(origins = "http://localhist:4200/")
    @GetMapping
    @Operation(summary = "get all vehicles form the bata base")
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        List<Vehicle> response = vehicleCrud.findAllVehicles();
        return ResponseEntity.ok(response);
    }


    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    @Operation(summary = "get just one vehicle depending of the id on url")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable("id") Long id){
        //Optional<Vehicle> vehicleResponse = vehiclesDAO.findById(id).map(VehiclesMapper::entityToDomain);
        //Otra forma
        //return vehicleResponse.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());

        Optional<Vehicle> vehicleResponse = vehicleCrud.findVehicleById(id);
        if(vehicleResponse.isPresent()){
            return ResponseEntity.ok(vehicleResponse.get());
        }else{
            return ResponseEntity.noContent().build();
        }
    }
    @CrossOrigin(origins = "*")
    @PostMapping
    @Operation(summary = "Save a new vehicle on dat base")
    public ResponseEntity<Vehicle> saveNewVehicle(@RequestBody Vehicle vehicleSave){
        Vehicle vehicle = vehicleCrud.insertVehicle(vehicleSave);
        return ResponseEntity.ok(vehicle);
    }
    @CrossOrigin(origins = "*")
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a vehicle with the id passed on url")
    public ResponseEntity deleteVehicle(@PathVariable("id") Long id){
        vehicleCrud.deleteVehicle(id);
        return ResponseEntity.ok().build();
    }
    @CrossOrigin(origins = "*")
    @PutMapping
    @Operation(summary = "upadte  a vehicle from database")
    public ResponseEntity<Optional<Vehicle>> updateVehicle(@RequestBody Vehicle updatedVehicle){
        //Optional<Vehicle> optionalVehicle= vehiclesDAO.findById(updatedVehicle.getId());
        Optional<Vehicle> optionalVehicle= Optional.ofNullable(vehicleCrud.updateVehicle(updatedVehicle));


        if(optionalVehicle.isPresent()){
              return ResponseEntity.ok(optionalVehicle);
        }else{ return ResponseEntity.notFound().build();}
    }

}


