package com.example.rent_a_car.databaseRepository;

import com.example.rent_a_car.entities.Assign;

public class AssignMapper {

    public static Assign entityToDomain(AssignEntity assignEntity){
        Assign assign = new Assign();
        assign.setId(assignEntity.getId());
        assign.setVehicle_id(assignEntity.getVehicle_id());
        assign.setClient_id(assignEntity.getClient_id());
        assign.setDate(assignEntity.getDate());
        return assign;
    }

    public static AssignEntity domainToentity(Assign assign){
        AssignEntity assignEntity = new AssignEntity();
        assignEntity.setId(assign.getId());
        assignEntity.setVehicle_id(assign.getVehicle_id());
        assignEntity.setClient_id(assign.getClient_id());
        assignEntity.setDate(assign.getDate());

        return assignEntity;
    }
}
