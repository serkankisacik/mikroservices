package com.example.filterService.api.controllers;

import com.example.filterService.business.abstracts.FilterService;
import com.example.filterService.business.responses.GetAllFiltersResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/filters")
public class FilterController {
    private final FilterService service;

    @GetMapping
    public List<GetAllFiltersResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/brand")
    public List<GetAllFiltersResponse> getByBrandName(@RequestParam String brandName) {
        return service.getByBrandName(brandName);
    }
}
