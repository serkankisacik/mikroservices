package com.example.inventoryService.concretes;

import com.example.inventoryService.business.abstracts.CarService;
import com.example.inventoryService.business.requests.create.CreateCarRequest;
import com.example.inventoryService.business.responses.create.CreateCarResponse;
import com.example.inventoryService.business.responses.get.GetAllCarResponse;

import java.util.List;

public class CarManager implements CarService {
    @Override
    public List<GetAllCarResponse> getAll() {
        return null;
    }

    @Override
    public CreateCarResponse add(CreateCarRequest createCarRequest) {
        return null;
    }
}
