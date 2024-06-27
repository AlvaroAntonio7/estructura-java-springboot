package com.example.rent_a_car.databaseRepository;


import com.example.rent_a_car.entities.Client;

public class ClientsMapper {

    public static Client entityToDomain(ClientEntity clientEntity){
        Client client = new Client();
        client.setId(clientEntity.getId());
        client.setFirst_name(clientEntity.getFirst_name());
        client.setSecond_last_name(clientEntity.getSecond_last_name());
        client.setLast_name(clientEntity.getLast_name());
        client.setGender(clientEntity.getGender());
        client.setBirthday(clientEntity.getBirthday());
        client.setDocument_type(clientEntity.getDocument_type());
        client.setDocument_number(clientEntity.getDocument_number());


        return client;
    }

    public static ClientEntity domainToentity(Client client){

        ClientEntity clientEntity= new ClientEntity();

        clientEntity.setId(client.getId());
        clientEntity.setFirst_name(client.getFirst_name());
        clientEntity.setSecond_last_name(client.getSecond_last_name());
        clientEntity.setLast_name(client.getLast_name());
        clientEntity.setGender(client.getGender());
        clientEntity.setBirthday(client.getBirthday());
        clientEntity.setDocument_type(client.getDocument_type());
        clientEntity.setDocument_number(client.getDocument_number());

        return clientEntity;
    }

}
