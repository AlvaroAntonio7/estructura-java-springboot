package com.example.rent_a_car.databaseRepository;

import com.example.rent_a_car.dao.VehiclesDAO;
import com.example.rent_a_car.entities.Vehicle;
import com.example.rent_a_car.port.VehicleCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class VehicleAdapter implements VehicleCrud {

    @Autowired
    private VehiclesDAO vehiclesDAO;

    public VehicleAdapter(VehiclesDAO vehiclesDAO) {
        this.vehiclesDAO = vehiclesDAO;
    }



    @Override
    public List<Vehicle> findAllVehicles() {
        List<Vehicle> response = vehiclesDAO.findAll().stream().map(VehiclesMapper::entityToDomain).toList();
        return response;
    }

    @Override
    public Optional<Vehicle> findVehicleById(Long id) {
        return vehiclesDAO.findById(id).map(VehiclesMapper::entityToDomain);
    }

    @Override
    public Vehicle updateVehicle(Vehicle vehicle) {

        Optional<VehicleEntity> optionalVehicle= vehiclesDAO.findById(vehicle.getId());

        if(optionalVehicle.isPresent()){
            VehicleEntity saveVehicle = optionalVehicle.get();
            saveVehicle.setBrand(vehicle.getBrand());
            saveVehicle.setModel(vehicle.getModel());
            saveVehicle.setYear(vehicle.getYear());
            saveVehicle.setPlate(vehicle.getPlate());

            vehiclesDAO.save(saveVehicle);
            return VehiclesMapper.entityToDomain(saveVehicle);
        }else{ return null;}
    }

    @Override
    public void deleteVehicle(Long id) {
        vehiclesDAO.deleteById(id);
    }

    @Override
    public Vehicle insertVehicle(Vehicle vehicle) {
        VehicleEntity newVehicle = vehiclesDAO.save(VehiclesMapper.domainToentity(vehicle));

        return VehiclesMapper.entityToDomain(newVehicle);
    }
}
