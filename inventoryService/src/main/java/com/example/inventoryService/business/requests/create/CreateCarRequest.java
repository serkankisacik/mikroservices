package com.example.inventoryService.business.requests.create;

import com.example.inventoryService.entities.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarRequest {

    private String dailyPrice;
    private int modelYear;

    private String plate;
    private int state;
    private Model model;
}
