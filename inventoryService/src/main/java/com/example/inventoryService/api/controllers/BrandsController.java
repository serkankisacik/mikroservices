package com.example.inventoryService.api.controllers;

import com.example.inventoryService.business.abstracts.BrandService;
import com.example.inventoryService.business.requests.create.CreateBrandRequest;
import com.example.inventoryService.business.requests.update.UpdateBrandRequest;
import com.example.inventoryService.business.responses.create.CreateBrandResponse;
import com.example.inventoryService.business.responses.get.GetAllBrandResponse;
import com.example.inventoryService.business.responses.get.GetBrandResponse;
import com.example.inventoryService.business.responses.update.UpdateBrandResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/brands")
public class BrandsController {
    private final BrandService service;

    @GetMapping
    public List<GetAllBrandResponse> getAll() {
        return service.getAll();
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public CreateBrandResponse add(@RequestBody CreateBrandRequest request) {
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdateBrandResponse update(@RequestBody UpdateBrandRequest request, @PathVariable String id) {
        return service.update(request, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        service.delete(id);
    }

    @GetMapping("/{id}")
    public GetBrandResponse getById(@PathVariable String id) {
        return service.getById(id);
    }
}
