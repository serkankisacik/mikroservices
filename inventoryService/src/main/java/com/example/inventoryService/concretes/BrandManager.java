package com.example.inventoryService.concretes;

import com.example.inventoryService.business.abstracts.BrandService;
import com.example.inventoryService.business.requests.create.CreateBrandRequest;
import com.example.inventoryService.business.requests.update.UpdateBrandRequest;
import com.example.inventoryService.business.responses.create.CreateBrandResponse;
import com.example.inventoryService.business.responses.get.GetAllBrandResponse;
import com.example.inventoryService.business.responses.get.GetBrandResponse;
import com.example.inventoryService.business.responses.update.UpdateBrandResponse;
import com.example.inventoryService.dataAccess.abstracts.BrandRepository;
import com.example.inventoryService.entities.Brand;
import com.kodlamaio.common.utilities.exceptions.BusinessException;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BrandManager implements BrandService {
    private final BrandRepository repository;
    private final ModelMapperService mapper;

    @Override
    public List<GetAllBrandResponse> getAll() {
        List<Brand> brands = repository.findAll();
        List<GetAllBrandResponse> response = brands
                .stream()
                .map(brand -> mapper.forResponse().map(brand, GetAllBrandResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetBrandResponse getById(String id) {
        checkIfBrandExistsById(id);
        Brand brand = repository.findById(id).orElseThrow();
        GetBrandResponse response = mapper.forResponse().map(brand, GetBrandResponse.class);

        return response;
    }

    @Override
    public CreateBrandResponse add(CreateBrandRequest request) {
        checkIfBrandExistsByName(request.getName());
        Brand brand = mapper.forRequest().map(request, Brand.class);
        brand.setId(UUID.randomUUID().toString());
        repository.save(brand);
        CreateBrandResponse response = mapper.forResponse().map(brand, CreateBrandResponse.class);

        return response;
    }

    @Override
    public UpdateBrandResponse update(UpdateBrandRequest request, String id) {
        checkIfBrandExistsById(id);
        Brand brand = mapper.forRequest().map(request, Brand.class);
        brand.setId(id);
        repository.save(brand);
        UpdateBrandResponse response = mapper.forResponse().map(brand, UpdateBrandResponse.class);

        return response;
    }

    @Override
    public void delete(String id) {
        checkIfBrandExistsById(id);
        repository.deleteById(id);
    }

    private void checkIfBrandExistsByName(String name) {
        if (repository.existsByNameIgnoreCase(name)) {
            throw new BusinessException("BRAND.EXISTS");
        }
    }

    private void checkIfBrandExistsById(String id) {
        if (!repository.existsById(id)) {
            throw new BusinessException("BRAND.NOT.EXISTS");
        }
    }
}
