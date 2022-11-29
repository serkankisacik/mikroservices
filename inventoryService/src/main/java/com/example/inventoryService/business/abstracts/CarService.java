package com.example.inventoryService.business.abstracts;

import com.example.inventoryService.business.requests.create.CreateCarRequest;
import com.example.inventoryService.business.responses.create.CreateBrandResponse;
import com.example.inventoryService.business.responses.create.CreateCarResponse;
import com.example.inventoryService.business.responses.get.GetAllCarResponse;

import java.util.List;

public interface CarService {
    List<GetAllCarResponse> getAll();
    CreateCarResponse add(CreateCarRequest createCarRequest);
}
