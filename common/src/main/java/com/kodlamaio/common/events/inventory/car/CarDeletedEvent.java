package com.kodlamaio.common.events.inventory.car;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDeletedEvent {
    private String carId;
}
