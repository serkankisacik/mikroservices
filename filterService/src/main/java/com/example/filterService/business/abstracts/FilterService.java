package com.example.filterService.business.abstracts;

import com.example.filterService.business.responses.GetAllFiltersResponse;
import com.example.filterService.business.responses.GetFilterResponse;
import com.example.filterService.entities.Filter;

import java.util.List;

public interface FilterService {
    List<GetAllFiltersResponse> getAll();
    List<GetAllFiltersResponse> getByBrandName(String brandName);

    List<GetAllFiltersResponse> getByModelName(String modelName);
    GetFilterResponse getByPlate(String plate);
    List<GetAllFiltersResponse> searchByPlate(String plate);
    List<GetAllFiltersResponse> searchByBrandName(String brandName);
    List<GetAllFiltersResponse> searchByModelName(String modelName);
    List<GetAllFiltersResponse> getByModelYear(int modelYear);
    List<GetAllFiltersResponse> getByState(int state);

    // Consumer service
    Filter getByCarId(String id);
    List<Filter> getByModelId(String modelId);
    List<Filter> getByBrandId(String brandId);
    void save(Filter mongodb);
    void delete(String id);
    void deleteAllByBrandId(String brandId);
    void deleteAllByModelId(String modelId);
}
