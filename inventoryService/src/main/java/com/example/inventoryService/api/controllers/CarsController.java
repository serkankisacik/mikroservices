package com.example.inventoryService.api.controllers;

import com.example.inventoryService.business.abstracts.CarService;
import com.example.inventoryService.business.requests.create.CreateCarRequest;
import com.example.inventoryService.business.requests.update.UpdateCarRequest;
import com.example.inventoryService.business.responses.create.CreateCarResponse;
import com.example.inventoryService.business.responses.get.GetAllCarResponse;
import com.example.inventoryService.business.responses.get.GetCarResponse;
import com.example.inventoryService.business.responses.update.UpdateCarResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/cars")
public class CarsController {
    private final CarService service;

    @GetMapping
    public List<GetAllCarResponse> getAll() {
        return service.getAll();
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public CreateCarResponse add(@RequestBody CreateCarRequest request) {
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdateCarResponse update(@RequestBody UpdateCarRequest request, @PathVariable String id) {
        return service.update(request, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        service.delete(id);
    }

    @GetMapping("/{id}")
    public GetCarResponse getById(@PathVariable String id) {
        return service.getById(id);
    }


    @GetMapping("/check-car-available/{id}")
    public void checkIfCarAvailable(@PathVariable String id) {
        service.checkIfCarAvailable(id);
    }

}
