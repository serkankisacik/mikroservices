package com.example.inventoryService.business.requests.create;

import com.example.inventoryService.entities.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarRequest {
    @Min(value = 0)
    private double dailyPrice;
    @Min(value = 2015)
    private int modelYear;
    @NotBlank
    @NotNull
    private String plate;
    @Min(value = 1)
    @Max(value = 3)
    private int state;
    @NotBlank
    @NotNull
    private String modelId;
}
