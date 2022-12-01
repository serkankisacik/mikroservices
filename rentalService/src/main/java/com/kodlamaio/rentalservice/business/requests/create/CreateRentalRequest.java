package com.kodlamaio.rentalservice.business.requests.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalRequest {
    private String carId;

    private int rentedForDays;
    private double dailyPrice;
    private double totalPrice;
}
