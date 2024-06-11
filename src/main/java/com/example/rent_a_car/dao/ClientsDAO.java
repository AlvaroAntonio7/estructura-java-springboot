package com.example.rent_a_car.dao;

import com.example.rent_a_car.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientsDAO extends JpaRepository<Client, Long> {
}
