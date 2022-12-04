package com.example.filterService.business.concretes;

import com.example.filterService.business.abstracts.FilterService;
import com.example.filterService.business.responses.GetAllFiltersResponse;
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
}
