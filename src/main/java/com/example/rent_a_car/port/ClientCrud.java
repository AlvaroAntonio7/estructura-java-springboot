package com.example.rent_a_car.port;



import com.example.rent_a_car.entities.Client;

import java.util.List;
import java.util.Optional;

public interface ClientCrud {
    public List<Client> findAllClients();

    public Optional<Client> findClientById(Long id);

    public Client updateClient(Client client);

    public void deleteClient(Long id);

    public Client insertclient(Client client);
}
