package com.example.rent_a_car.databaseRepository;

import com.example.rent_a_car.dao.ClientsDAO;
import com.example.rent_a_car.entities.Client;
import com.example.rent_a_car.port.ClientCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ClientAdapter implements ClientCrud {

    @Autowired
    private ClientsDAO clientsDAO;

    public ClientAdapter(ClientsDAO clientsDAO) {
        this.clientsDAO = clientsDAO;
    }

    @Override
    public List<Client> findAllClients() {

        List<Client> response = clientsDAO.findAll().stream().map(ClientsMapper::entityToDomain).toList();
        return response;
    }

    @Override
    public Optional<Client> findClientById(Long id) {
         return clientsDAO.findById(id).map(ClientsMapper::entityToDomain);

    }

    @Override
    public Client updateClient(Client client) {

       Optional<ClientEntity> optionalClient = clientsDAO.findById(client.getId());

        if(optionalClient.isPresent()){
            ClientEntity saveClient = optionalClient.get();
            saveClient.setFirst_name(client.getFirst_name());
            saveClient.setLast_name(client.getLast_name());
            saveClient.setSecond_last_name(client.getSecond_last_name());
            saveClient.setBirthday(client.getBirthday());
            saveClient.setGender(client.getGender());
            saveClient.setDocument_type(client.getDocument_type());
            saveClient.setDocument_number(client.getDocument_number());

            clientsDAO.save(saveClient);
            return ClientsMapper.entityToDomain(saveClient);
        }else{return null;}

    }

    @Override
    public void deleteClient(Long id) {
        clientsDAO.deleteById(id);
    }

    @Override
    public Client insertclient(Client client) {
        ClientEntity newClient = clientsDAO.save(ClientsMapper.domainToentity(client));
        return ClientsMapper.entityToDomain(newClient);
    }
}
