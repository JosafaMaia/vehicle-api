package br.gov.sefaz.vehicle_api.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    @Column(name = "vehicle_year")
    private int year;
    private  String brandModel;
    private String plate;
    private String previousPlate;
    private String renavam;
    private String chassi;
    private LocalDate startDate;
    private LocalDate endDate;
    private String origin;
    @Column(name = "origin_description", columnDefinition = "TEXT")
    private String originDescription;


    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public int getYear() {
        return year;
    }


    public String getBrandModel() {
        return brandModel;
    }

    public String getPlate() {
        return plate;
    }


    public String getPreviousPlate() {
        return previousPlate;
    }


    public String getRenavam() {
        return renavam;
    }


    public String getChassi() {
        return chassi;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }


    public String getOrigin() { return origin; }

    public String getOriginDescription() { return originDescription; }

}
