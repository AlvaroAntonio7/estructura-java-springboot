package com.example.rent_a_car.port;

import com.example.rent_a_car.entities.Assign;

import java.util.List;
import java.util.Optional;

public interface AssignCrud {
    public List<Assign> findAllAssigns();

    public Optional<Assign> findAssignById(Long id);

    public Optional<Assign> findAssignByVehicleId(String vehicleId);

    public Assign updateAssign(Assign assign);

    public void deleteAssign(Long id);

    public Assign insertAssign(Assign assign);
}
