package com.example.rent_a_car.controllers;

import com.example.rent_a_car.dao.ClientsDAO;

import com.example.rent_a_car.entities.Client;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
@Tag(name = "Documentacion del endpoint clients")
public class ClientsController {

    @Autowired
    private ClientsDAO clientsDAO;

    @GetMapping
    @Operation(summary = "get all vehicles form the bata base")
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> response = clientsDAO.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "get just one Client depending of the id on url")
    public ResponseEntity<Client> getClientById(@PathVariable("id") Long id){
        Optional<Client> ClientResponse = clientsDAO.findById(id);
        //Otra forma
        //return ClientResponse.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
        if(ClientResponse.isPresent()){
            return ResponseEntity.ok(ClientResponse.get());
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    @Operation(summary = "Save a new Client on dat base")
    public ResponseEntity<Client> saveNewClient(@RequestBody Client ClientSave){
        Client Client = clientsDAO.save(ClientSave);
        return ResponseEntity.ok(Client);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Client with the id passed on url")
    public ResponseEntity<String> deleteClient(@PathVariable("id") Long id){
        clientsDAO.deleteById(id);
        return ResponseEntity.ok("Dato Borrado");
    }

    @PutMapping
    @Operation(summary = "upadte  a Client from database")
    public ResponseEntity<Client> updateClient(@RequestBody Client updatedClient){
        Optional<Client> optionalClient= clientsDAO.findById(updatedClient.getId());

        if(optionalClient.isPresent()){
            Client saveClient = optionalClient.get();
            saveClient.setFirst_name(updatedClient.getFirst_name());
                    saveClient.setLast_name(updatedClient.getLast_name());
                    saveClient.setSecond_last_name(updatedClient.getSecond_last_name());
                    saveClient.setBirthday(updatedClient.getBirthday());
                    saveClient.setDocument_type(updatedClient.getDocument_type());
                    saveClient.setDocument_number(updatedClient.getDocument_number());
                    saveClient.setGender(updatedClient.getGender());


            clientsDAO.save(saveClient);
            return ResponseEntity.ok(saveClient);
        }else{ return ResponseEntity.notFound().build();}
    }
}
