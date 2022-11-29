package com.example.inventoryService.concretes;

import com.example.inventoryService.business.abstracts.BrandService;
import com.example.inventoryService.business.requests.create.CreateBrandRequest;
import com.example.inventoryService.business.responses.create.CreateBrandResponse;
import com.example.inventoryService.business.responses.get.GetAllBrandResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandManager implements BrandService {
    @Override
    public List<GetAllBrandResponse> getAll() {
        return null;
    }

    @Override
    public CreateBrandResponse add(CreateBrandRequest createBrandRequest) {
        return null;
    }
}
