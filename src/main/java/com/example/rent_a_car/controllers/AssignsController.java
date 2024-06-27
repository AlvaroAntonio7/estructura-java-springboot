package com.example.rent_a_car.controllers;

import com.example.rent_a_car.dao.AssignsDAO;
import com.example.rent_a_car.entities.Assign;
import com.example.rent_a_car.port.AssignCrud;
import com.example.rent_a_car.port.VerifyInformation;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/assigns")
public class AssignsController {

    AssignCrud assignCrud;
    private final VerifyInformation verifyInformation;

    public AssignsController(AssignCrud assignCrud
            ,  VerifyInformation verifyInformation
                             ) {
        this.assignCrud = assignCrud;
        this.verifyInformation = verifyInformation;

    }


    @CrossOrigin(origins = "*")
    @GetMapping
    @Operation(summary = "get all Assigns form the bata base")
    public ResponseEntity<List<Assign>> getAllAssigns() {
        List<Assign> response = assignCrud.findAllAssigns();
        return ResponseEntity.ok(response);
    }


    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    @Operation(summary = "get just one Assign depending of the id on url")
    public ResponseEntity<Assign> getAssignById(@PathVariable("id") Long id){

        Optional<Assign> AssignResponse = assignCrud.findAssignById(id);
        //Otra forma
        //return AssignResponse.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
        if(AssignResponse.isPresent()){
            return ResponseEntity.ok(AssignResponse.get());
        }else{
            return ResponseEntity.noContent().build();
        }
    }


    @CrossOrigin(origins = "*")
    @PostMapping
    @Operation(summary = "Save a new Assign on dat base")
    public ResponseEntity<Assign> saveNewAssign(@RequestBody Assign assignSave){
        Assign Assign = verifyInformation.saveNewAssign(assignSave);
        return ResponseEntity.ok(Assign);
        //return null;
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Assign with the id passed on url")
    public ResponseEntity<String> deleteAssign(@PathVariable("id") Long id){
        assignCrud.deleteAssign(id);
        return ResponseEntity.ok("Dato Borrado");
    }


    @CrossOrigin(origins = "*")
    @PutMapping
    @Operation(summary = "upadte  a Assign from database")
    public ResponseEntity<Assign> updateAssign(@RequestBody Assign updatedAssign){ //TODO: hacer usando useCase
        Optional<Assign> optionalAssign= assignCrud.findAssignById(updatedAssign.getId());

        if(optionalAssign.isPresent()){
            Assign saveAssign = optionalAssign.get();
            saveAssign.setClient_id(updatedAssign.getClient_id());
                    saveAssign.setVehicle_id(updatedAssign.getVehicle_id());
                    saveAssign.setDate(updatedAssign.getDate());

            assignCrud.insertAssign(saveAssign);
            return ResponseEntity.ok(saveAssign);
        }else{ return ResponseEntity.notFound().build();}
    }




}
