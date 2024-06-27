package com.example.rent_a_car.databaseRepository;

import jakarta.persistence.*;

@Entity
@Table(name="Vehicles2")
public class VehicleEntity {

    @Id
    //@Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   // @Column(name="brand")
    private String brand;

    //@Column(name="model")
    private String model;

   // @Column(name="year")
    private Integer year;

   //@Column(name="plate")
    private String plate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }
}
