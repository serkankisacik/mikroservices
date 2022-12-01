package com.kodlamaio.rentalservice.business.requests.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentalRequest {
    private String id;
    private String carId;
    private LocalDateTime dateStarted;
    private int rentedForDays;
    private double dailyPrice;
    private double totalPrice;
}
