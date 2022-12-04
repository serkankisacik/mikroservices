package com.example.filterService.repository;

import com.example.filterService.entities.Filter;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FilterRepository extends MongoRepository<Filter, String> {
    List<Filter> findByBrandNameIgnoreCase(String brandName);
    List<Filter> findByModelNameIgnoreCase(String modelName);
}
