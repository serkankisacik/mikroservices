package com.example.inventoryService.business.abstracts;

import com.example.inventoryService.business.requests.create.CreateBrandRequest;
import com.example.inventoryService.business.responses.create.CreateBrandResponse;
import com.example.inventoryService.business.responses.get.GetAllBrandResponse;

import java.util.List;

public interface BrandService {
    List<GetAllBrandResponse> getAll();

    CreateBrandResponse add(CreateBrandRequest createBrandRequest);
}
