package com.example.filterService.business.concretes;

import com.example.filterService.business.abstracts.FilterService;
import com.example.filterService.business.responses.GetAllFiltersResponse;
import com.example.filterService.business.responses.GetFilterResponse;
import com.example.filterService.entities.Filter;
import com.example.filterService.repository.FilterRepository;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FilterManager implements FilterService {
    private final FilterRepository repository;
    private final ModelMapperService mapper;

    @Override
    public List<GetAllFiltersResponse> getAll() {
        List<Filter> filters = repository.findAll();
        List<GetAllFiltersResponse> response = filters
                .stream()
                .map(filter -> mapper.forResponse().map(filter, GetAllFiltersResponse.class))
                .toList();
        return response;
    }

    @Override
    public List<GetAllFiltersResponse> getByBrandName(String brandName) {
        List<Filter> filters = repository.findByBrandNameIgnoreCase(brandName);
        List<GetAllFiltersResponse> response = filters
                .stream()
                .map(filter -> mapper.forResponse().map(filter, GetAllFiltersResponse.class))
                .toList();
        return response;
    }

    @Override
    public List<GetAllFiltersResponse> getByModelName(String modelName) {
        List<Filter> filters = repository.findByModelNameIgnoreCase(modelName);
        List<GetAllFiltersResponse> response = filters
                .stream()
                .map(filter -> mapper.forResponse().map(filter, GetAllFiltersResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetFilterResponse getByPlate(String plate) {
        checkIfExistByPlate(plate);
        Filter filter = repository.findByPlateIgnoreCase(plate);
        GetFilterResponse response = mapper.forResponse().map(filter, GetFilterResponse.class);

        return response;
    }

    @Override
    public List<GetAllFiltersResponse> searchByPlate(String plate) {
        List<Filter> filters = repository.findByPlateContainingIgnoreCase(plate);
        List<GetAllFiltersResponse> response = filters
                .stream()
                .map(filter -> mapper.forResponse().map(filter, GetAllFiltersResponse.class))
                .toList();

        return response;    }

    @Override
    public List<GetAllFiltersResponse> searchByBrandName(String brandName) {
        List<Filter> filters = repository.findByBrandNameContainingIgnoreCase(brandName);
        List<GetAllFiltersResponse> response = filters
                .stream()
                .map(filter -> mapper.forResponse().map(filter, GetAllFiltersResponse.class))
                .toList();

        return response;
    }

    @Override
    public List<GetAllFiltersResponse> searchByModelName(String modelName) {
        List<Filter> filters = repository.findByModelNameContainingIgnoreCase(modelName);
        List<GetAllFiltersResponse> response = filters
                .stream()
                .map(filter -> mapper.forResponse().map(filter, GetAllFiltersResponse.class))
                .toList();

        return response;
    }

    @Override
    public List<GetAllFiltersResponse> getByModelYear(int modelYear) {
        List<Filter> filters = repository.findByModelYear(modelYear);
        List<GetAllFiltersResponse> response = filters
                .stream()
                .map(filter -> mapper.forResponse().map(filter, GetAllFiltersResponse.class))
                .toList();

        return response;
    }

    @Override
    public List<GetAllFiltersResponse> getByState(int state) {
        List<Filter> filters = repository.findByState(state);
        List<GetAllFiltersResponse> response = filters
                .stream()
                .map(filter -> mapper.forResponse().map(filter, GetAllFiltersResponse.class))
                .toList();

        return response;
    }

    @Override
    public Filter getByCarId(String id) {
        return repository.findByCarId(id);
    }

    @Override
    public List<Filter> getByModelId(String modelId) {
        return repository.findByModelId(modelId);
    }

    @Override
    public List<Filter> getByBrandId(String brandId) {
        return repository.findByBrandId(brandId);
    }

    @Override
    public void save(Filter filter) {
        repository.save(filter);
    }

    @Override
    public void delete(String id) {
        repository.deleteByCarId(id);
    }

    @Override
    public void deleteAllByBrandId(String brandId) {
        repository.deleteAllByBrandId(brandId);
    }

    @Override
    public void deleteAllByModelId(String modelId) {
        repository.deleteAllByModelId(modelId);
    }

    private void checkIfExistByPlate(String plate) {
        if (!repository.existsByPlate(plate)) {
            throw new RuntimeException("CAR_NOT_EXISTS");
        }
    }
}
