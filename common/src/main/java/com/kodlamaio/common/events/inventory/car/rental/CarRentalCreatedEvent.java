package com.kodlamaio.common.events.inventory.car.rental;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarRentalCreatedEvent {
    private String message;
    private String carId;
}
