package com.example.inventoryService.business.abstracts;

import com.example.inventoryService.business.requests.create.CreateModelRequest;
import com.example.inventoryService.business.responses.create.CreateModelResponse;
import com.example.inventoryService.business.responses.get.GetAllModelResponse;

import java.util.List;

public interface ModelService {
    List<GetAllModelResponse> getAll();
    CreateModelResponse add(CreateModelRequest createModelRequest);
}
