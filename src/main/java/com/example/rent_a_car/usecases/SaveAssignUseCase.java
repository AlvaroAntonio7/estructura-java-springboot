package com.example.rent_a_car.usecases;

import com.example.rent_a_car.databaseRepository.AssignEntity;
import com.example.rent_a_car.databaseRepository.AssignMapper;
import com.example.rent_a_car.entities.Assign;
import com.example.rent_a_car.port.AssignCrud;
import com.example.rent_a_car.port.VerifyInformation;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SaveAssignUseCase implements VerifyInformation {

    private final AssignCrud assignCrud;

    public SaveAssignUseCase(AssignCrud assignCrud) {
        this.assignCrud = assignCrud;
    }

    @Override
    public Assign saveNewAssign(Assign assign) {

        //buscar en la tabla assigns por el id de vehiculo
        //si ese id de vehiculo ya esta registrado devolver no se puede
        //Si no se encuentra ese id devehiculo guardar

        Optional<Assign> isAllreadyAssign = assignCrud.findAssignByVehicleId(assign.getVehicle_id());
        //Optional<Assign> isAllreadyAssign = assignCrud.findAssignById(assign.getId());

        if(isAllreadyAssign.isPresent()){
            return null;
        }else{
            Assign newAssign =assignCrud.insertAssign(assign);
            return newAssign;
        }


    }
}
