package com.example.rent_a_car.databaseRepository;

import com.example.rent_a_car.dao.AssignsDAO;
import com.example.rent_a_car.entities.Assign;
import com.example.rent_a_car.port.AssignCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AssignAdapter implements AssignCrud {

    @Autowired
    AssignsDAO assignsDAO;

    public AssignAdapter(AssignsDAO assignsDAO) {
        this.assignsDAO = assignsDAO;
    }

    @Override
    public List<Assign> findAllAssigns() {
        List<Assign> response = assignsDAO.findAll().stream().map(AssignMapper::entityToDomain).toList();
        return response;
    }

    @Override
    public Optional<Assign> findAssignById(Long id) {

        return assignsDAO.findById(id).map(AssignMapper::entityToDomain);
    }

    @Override
    //@Query(value="SELECT * FROM assigns2 WHERE vehicle_id = :vehicleId", nativeQuery = true)
    public Optional<Assign> findAssignByVehicleId(String vehicleId) {
       return assignsDAO.findByVehicle_id(vehicleId).map(AssignMapper::entityToDomain);
    }



    @Override
    public Assign updateAssign(Assign assign) {

        Optional<AssignEntity> optionalAssign = assignsDAO.findById(assign.getId());

        if(optionalAssign.isPresent()){
            AssignEntity saveAssign = optionalAssign.get();
            saveAssign.setClient_id(assign.getClient_id());
            saveAssign.setVehicle_id(assign.getVehicle_id());
            saveAssign.setDate(assign.getDate());

            assignsDAO.save(saveAssign);
            return AssignMapper.entityToDomain(saveAssign);
        }else{return null;}


    }

    @Override
    public void deleteAssign(Long id) {
        assignsDAO.deleteById(id);
    }

    @Override
    public Assign insertAssign(Assign assign) {
        AssignEntity newAssign = assignsDAO.save(AssignMapper.domainToentity(assign));
         return AssignMapper.entityToDomain(newAssign);
    }
}
