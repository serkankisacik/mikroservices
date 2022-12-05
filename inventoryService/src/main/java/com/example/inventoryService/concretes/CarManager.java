package com.example.inventoryService.concretes;

import com.example.inventoryService.business.abstracts.CarService;
import com.example.inventoryService.business.requests.create.CreateCarRequest;
import com.example.inventoryService.business.requests.update.UpdateCarRequest;
import com.example.inventoryService.business.responses.create.CreateCarResponse;
import com.example.inventoryService.business.responses.get.GetAllCarResponse;
import com.example.inventoryService.business.responses.get.GetCarResponse;
import com.example.inventoryService.business.responses.update.UpdateCarResponse;
import com.example.inventoryService.dataAccess.abstracts.CarRepository;
import com.example.inventoryService.entities.Car;
import com.kodlamaio.common.utilities.exceptions.BusinessException;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CarManager implements CarService {
    private final CarRepository repository;
    private final ModelMapperService mapper;

    @Override
    public List<GetAllCarResponse> getAll() {
        List<Car> cars = repository.findAll();
        List<GetAllCarResponse> response = cars
                .stream()
                .map(car -> mapper.forResponse().map(car, GetAllCarResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetCarResponse getById(String id) {
        checkIfCarExistsById(id);
        Car car = repository.findById(id).orElseThrow();
        GetCarResponse response = mapper.forResponse().map(car, GetCarResponse.class);

        return response;
    }

    @Override
    public CreateCarResponse add(CreateCarRequest request) {
        checkIfCarExistsByPlate(request.getPlate());
        Car car = mapper.forRequest().map(request, Car.class);
        car.setId(UUID.randomUUID().toString());
        repository.save(car);
        CreateCarResponse response = mapper.forResponse().map(car, CreateCarResponse.class);
        return response;
    }

    @Override
    public UpdateCarResponse update(UpdateCarRequest request, String id) {
        checkIfCarExistsById(id);
        checkIfCarExistsByPlate(request.getPlate());
        Car car = mapper.forRequest().map(request, Car.class);
        car.setId(id);
        repository.save(car);
        UpdateCarResponse response = mapper.forResponse().map(car, UpdateCarResponse.class);

        return response;
    }

    @Override
    public void delete(String id) {
        checkIfCarExistsById(id);
        repository.deleteById(id);
    }

    private void checkIfCarExistsById(String id) {
        if (!repository.existsById(id)) {
            throw new BusinessException("CAR.NOT_EXISTS");
        }
    }

    private void checkIfCarExistsByPlate(String plate) {
        if (repository.existsByPlateIgnoreCase(plate)) {
            throw new BusinessException("CAR.ALREADY_EXISTS");
        }
    }

    @Override
    public void checkIfCarAvailable(String id) {
        Car car = repository.findById(id).get();
        if (car.getState() == 3) {
            throw new BusinessException("CAR.NOT_AVAILABLE");
        }
    }
}
