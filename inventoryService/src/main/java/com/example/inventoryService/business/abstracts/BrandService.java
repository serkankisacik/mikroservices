package com.example.inventoryService.business.abstracts;

import com.example.inventoryService.business.requests.create.CreateBrandRequest;
import com.example.inventoryService.business.requests.update.UpdateBrandRequest;
import com.example.inventoryService.business.responses.create.CreateBrandResponse;
import com.example.inventoryService.business.responses.get.GetAllBrandResponse;
import com.example.inventoryService.business.responses.get.GetBrandResponse;
import com.example.inventoryService.business.responses.update.UpdateBrandResponse;

import java.util.List;

public interface BrandService {
    List<GetAllBrandResponse> getAll();

    CreateBrandResponse add(CreateBrandRequest createBrandRequest);

    GetBrandResponse getById(String id);

    UpdateBrandResponse update(UpdateBrandRequest request, String id);
    void delete(String id);
}
