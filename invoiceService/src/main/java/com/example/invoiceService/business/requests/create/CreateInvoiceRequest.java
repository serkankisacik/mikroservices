package com.example.invoiceService.business.requests.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateInvoiceRequest {

    private String number;
    private String userId;
    private String paymentId;
    private LocalDateTime date;
    @NotBlank
    private String carId;
    @NotBlank
    private String fullName;
    @NotBlank
    private String modelName;
    @NotBlank
    private String brandName;
    @NotNull
    @Min(1886)
    private int modelYear;
    @Min(0)
    private double dailyPrice;
    @Min(0)
    private double totalPrice;
    @Min(0)
    private int rentedForDays;
}
