package com.example.rent_a_car.controllers;

import com.example.rent_a_car.entities.Client;
import com.example.rent_a_car.port.ClientCrud;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
@Tag(name = "Documentacion del endpoint clients")
public class ClientsController {

   public final ClientCrud clientCrud;

    public ClientsController(ClientCrud clientCrud) {
        this.clientCrud = clientCrud;
    }

    @CrossOrigin(origins = "*") //especifica origenes, puede especificar el dominio exacto o * que es cualquier origen
    @GetMapping
    @Operation(summary = "get all vehicles form the bata base")
    public ResponseEntity<List<Client>> getAllClients() {

        List<Client> response = clientCrud.findAllClients();
        return ResponseEntity.ok(response);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    @Operation(summary = "get just one Client depending of the id on url")
    public ResponseEntity<Client> getClientById(@PathVariable("id") Long id){

        Optional<Client> clientResponse = clientCrud.findClientById(id);
        if(clientResponse.isPresent()){
            return ResponseEntity.ok(clientResponse.get());
        }else{return ResponseEntity.noContent().build();}
    }
    @CrossOrigin(origins = "*")
    @PostMapping
    @Operation(summary = "Save a new Client on dat base")
    public ResponseEntity<Client> saveNewClient(@RequestBody Client clientSave){

        Client Client = clientCrud.insertclient(clientSave);
        return ResponseEntity.ok(Client);
    }
    @CrossOrigin(origins = "*")
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Client with the id passed on url")
    public ResponseEntity<String> deleteClient(@PathVariable("id") Long id){
        clientCrud.deleteClient(id);
        return ResponseEntity.ok("Dato Borrado");
    }
    @CrossOrigin(origins = "*")
    @PutMapping
    @Operation(summary = "upadte  a Client from database")
    public ResponseEntity<Optional<Client>> updateClient(@RequestBody Client updatedClient) {

        Optional<Client> optionalClient = Optional.ofNullable(clientCrud.updateClient(updatedClient));
        if (optionalClient.isPresent()) {
            return ResponseEntity.ok(optionalClient);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

}
