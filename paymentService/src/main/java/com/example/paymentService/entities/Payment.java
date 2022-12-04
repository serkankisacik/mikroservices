package com.example.paymentService.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "userId")
    private String userId;

    @Column(name = "carId")
    private String carId;

    @Column(name = "days")
    private int days;

    @Column(name = "price")
    private double price;

    @Column(name = "tax")
    private double tax;

    @Column(name = "amount")
    private double amount;

}
