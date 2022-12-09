package com.kodlamaio.rentalservice.business.requests.create;

import com.kodlamaio.common.dto.CreatePaymentRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalRequest {
    private String carId;
    private String userId;
    private int rentedForDays;
    private double dailyPrice;
    private double totalPrice;
    private CreatePaymentRequestDTO paymentRequest;
}
