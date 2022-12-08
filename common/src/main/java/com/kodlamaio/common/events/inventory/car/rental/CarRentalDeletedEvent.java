package com.kodlamaio.common.events.inventory.car.rental;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarRentalDeletedEvent {
    private String message;
    private String carId;
}
