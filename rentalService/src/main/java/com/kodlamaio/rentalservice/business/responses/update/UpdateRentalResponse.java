package com.kodlamaio.rentalservice.business.responses.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentalResponse {
    private String id;
    private String carId;
    private LocalDateTime dateStarted;
    private int rentedForDays;
    private double dailyPrice;
    private double totalPrice;
}
