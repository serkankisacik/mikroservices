package com.example.inventoryService.api.controllers;

import com.example.inventoryService.business.abstracts.ModelService;
import com.example.inventoryService.business.requests.create.CreateModelRequest;
import com.example.inventoryService.business.requests.update.UpdateModelRequest;
import com.example.inventoryService.business.responses.create.CreateModelResponse;
import com.example.inventoryService.business.responses.get.GetAllModelResponse;
import com.example.inventoryService.business.responses.get.GetModelResponse;
import com.example.inventoryService.business.responses.update.UpdateModelResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/models")
public class ModelsController {
    private final ModelService service;

    @GetMapping
    public List<GetAllModelResponse> getAll() {
        return service.getAll();
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public CreateModelResponse add(@RequestBody CreateModelRequest request) {
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdateModelResponse update(@RequestBody UpdateModelRequest request, @PathVariable String id) {
        return service.update(request, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        service.delete(id);
    }

    @GetMapping("/{id}")
    public GetModelResponse getById(@PathVariable String id) {
        return service.getById(id);
    }
}
