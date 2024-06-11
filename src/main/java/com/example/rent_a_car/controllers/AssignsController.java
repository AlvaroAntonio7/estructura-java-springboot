package com.example.rent_a_car.controllers;

import com.example.rent_a_car.dao.AssignsDAO;
import com.example.rent_a_car.entities.Assign;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/assigns")
public class AssignsController {
    
    @Autowired
    private AssignsDAO assignsDAO;

    @GetMapping
    @Operation(summary = "get all Assigns form the bata base")
    public ResponseEntity<List<Assign>> getAllAssigns() {
        List<Assign> response = assignsDAO.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "get just one Assign depending of the id on url")
    public ResponseEntity<Assign> getAssignById(@PathVariable("id") Long id){
        Optional<Assign> AssignResponse = assignsDAO.findById(id);
        //Otra forma
        //return AssignResponse.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
        if(AssignResponse.isPresent()){
            return ResponseEntity.ok(AssignResponse.get());
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    @Operation(summary = "Save a new Assign on dat base")
    public ResponseEntity<Assign> saveNewAssign(@RequestBody Assign AssignSave){
        Assign Assign = assignsDAO.save(AssignSave);
        return ResponseEntity.ok(Assign);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Assign with the id passed on url")
    public ResponseEntity<String> deleteAssign(@PathVariable("id") Long id){
        assignsDAO.deleteById(id);
        return ResponseEntity.ok("Dato Borrado");
    }

    @PutMapping
    @Operation(summary = "upadte  a Assign from database")
    public ResponseEntity<Assign> updateAssign(@RequestBody Assign updatedAssign){
        Optional<Assign> optionalAssign= assignsDAO.findById(updatedAssign.getId());

        if(optionalAssign.isPresent()){
            Assign saveAssign = optionalAssign.get();
            saveAssign.setClient_id(updatedAssign.getClient_id());
                    saveAssign.setVehicle_id(updatedAssign.getVehicle_id());
                    saveAssign.setDate(updatedAssign.getDate());

            assignsDAO.save(saveAssign);
            return ResponseEntity.ok(saveAssign);
        }else{ return ResponseEntity.notFound().build();}
    }
}
