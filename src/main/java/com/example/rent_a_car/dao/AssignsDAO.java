package com.example.rent_a_car.dao;

import com.example.rent_a_car.entities.Assign;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignsDAO extends JpaRepository<Assign, Long> {
}
