package com.example.filterService.business.abstracts;

import com.example.filterService.business.responses.GetAllFiltersResponse;

import java.util.List;

public interface FilterService {
    List<GetAllFiltersResponse> getAll();
    List<GetAllFiltersResponse> getByBrandName(String brandName);
}
