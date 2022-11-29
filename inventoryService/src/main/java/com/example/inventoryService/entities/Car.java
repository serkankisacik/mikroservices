package com.example.inventoryService.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "dailyPrice")
    private String dailyPrice;

    @Column(name = "modelYear")
    private int modelYear;

    @Column(name = "plate")
    private String plate;

    @Column(name = "state")
    private int state;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;
}
