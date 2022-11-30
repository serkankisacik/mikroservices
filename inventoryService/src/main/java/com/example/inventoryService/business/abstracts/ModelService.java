package com.example.inventoryService.business.abstracts;

import com.example.inventoryService.business.requests.create.CreateModelRequest;
import com.example.inventoryService.business.requests.update.UpdateModelRequest;
import com.example.inventoryService.business.responses.create.CreateModelResponse;
import com.example.inventoryService.business.responses.get.GetAllModelResponse;
import com.example.inventoryService.business.responses.get.GetModelResponse;
import com.example.inventoryService.business.responses.update.UpdateModelResponse;

import java.util.List;

public interface ModelService {
    List<GetAllModelResponse> getAll();
    CreateModelResponse add(CreateModelRequest createModelRequest);

    GetModelResponse getById(String id);

    UpdateModelResponse update(UpdateModelRequest request, String id);

    void delete(String id);
}
