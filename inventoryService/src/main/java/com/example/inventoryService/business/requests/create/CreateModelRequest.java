package com.example.inventoryService.business.requests.create;

import com.example.inventoryService.entities.Brand;
import com.example.inventoryService.entities.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateModelRequest {
    private String name;
    private Brand brand;
    List<Car> cars;
}
