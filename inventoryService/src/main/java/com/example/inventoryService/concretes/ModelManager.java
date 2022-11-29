package com.example.inventoryService.concretes;

import com.example.inventoryService.business.abstracts.ModelService;
import com.example.inventoryService.business.requests.create.CreateModelRequest;
import com.example.inventoryService.business.responses.create.CreateModelResponse;
import com.example.inventoryService.business.responses.get.GetAllModelResponse;

import java.util.List;

public class ModelManager implements ModelService {
    @Override
    public List<GetAllModelResponse> getAll() {
        return null;
    }

    @Override
    public CreateModelResponse add(CreateModelRequest createModelRequest) {
        return null;
    }
}
