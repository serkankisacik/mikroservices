package com.example.inventoryService.concretes;

import com.example.inventoryService.business.abstracts.ModelService;
import com.example.inventoryService.business.requests.create.CreateModelRequest;
import com.example.inventoryService.business.requests.update.UpdateModelRequest;
import com.example.inventoryService.business.responses.create.CreateModelResponse;
import com.example.inventoryService.business.responses.get.GetAllModelResponse;
import com.example.inventoryService.business.responses.get.GetModelResponse;
import com.example.inventoryService.business.responses.update.UpdateModelResponse;
import com.example.inventoryService.dataAccess.abstracts.ModelRepository;
import com.example.inventoryService.entities.Model;
import com.kodlamaio.common.utilities.exceptions.BusinessException;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ModelManager implements ModelService {
    private final ModelRepository repository;
    private final ModelMapperService mapper;

    @Override
    public List<GetAllModelResponse> getAll() {
        List<Model> models = repository.findAll();
        List<GetAllModelResponse> response = models
                .stream()
                .map(model -> mapper.forResponse().map(model, GetAllModelResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetModelResponse getById(String id) {
        checkIfExistsById(id);
        Model model = repository.findById(id).orElseThrow();
        GetModelResponse response = mapper.forResponse().map(model, GetModelResponse.class);

        return response;
    }

    @Override
    public CreateModelResponse add(CreateModelRequest request) {
        checkIfExistsByName(request.getName());
        Model model = mapper.forRequest().map(request, Model.class);
        model.setId(UUID.randomUUID().toString());
        repository.save(model);
        CreateModelResponse response = mapper.forResponse().map(model, CreateModelResponse.class);

        return response;
    }

    @Override
    public UpdateModelResponse update(UpdateModelRequest request, String id) {
        checkIfExistsById(id);
        checkIfExistsByName(request.getName());
        Model model = mapper.forRequest().map(request, Model.class);
        model.setId(id);
        repository.save(model);
        UpdateModelResponse response = mapper.forResponse().map(model, UpdateModelResponse.class);

        return response;
    }

    @Override
    public void delete(String id) {
        checkIfExistsById(id);
        repository.deleteById(id);
    }

    private void checkIfExistsById(String id) {
        if (!repository.existsById(id)) {
            throw new BusinessException("MODE.NOT.EXISTS");
        }
    }

    private void checkIfExistsByName(String name) {
        if (repository.existsByNameIgnoreCase(name)) {
            throw new BusinessException("MODEL.EXISTS");
        }
    }
}
