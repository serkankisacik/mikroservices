package com.kodlamaio.common.events.inventory.car.rental;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarRentalUpdatedEvent {
    private String message;
    private String newCarId;
    private String oldCarId;
}
