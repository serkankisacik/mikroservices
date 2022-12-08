package com.example.filterService.kafka;

import com.example.filterService.business.abstracts.FilterService;
import com.example.filterService.entities.Filter;
import com.kodlamaio.common.events.inventory.InventoryCreatedEvent;
import com.kodlamaio.common.events.inventory.brand.BrandDeletedEvent;
import com.kodlamaio.common.events.inventory.brand.BrandUpdatedEvent;
import com.kodlamaio.common.events.inventory.car.CarDeletedEvent;
import com.kodlamaio.common.events.inventory.car.CarUpdatedEvent;
import com.kodlamaio.common.events.inventory.car.rental.CarRentalCreatedEvent;
import com.kodlamaio.common.events.inventory.car.rental.CarRentalDeletedEvent;
import com.kodlamaio.common.events.inventory.car.rental.CarRentalUpdatedEvent;
import com.kodlamaio.common.events.inventory.model.ModelDeletedEvent;
import com.kodlamaio.common.events.inventory.model.ModelUpdatedEvent;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class InventoryConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryConsumer.class);
    private final FilterService service;
    private final ModelMapperService mapper;

    public InventoryConsumer(FilterService service, ModelMapperService mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @KafkaListener(
            topics = "${spring.kafka.topic.name}"
            , groupId = "inventory-create"
    )
    public void consume(InventoryCreatedEvent event) {
        Filter filter = mapper.forRequest().map(event, Filter.class);
        service.save(filter);
        LOGGER.info("Inventory created event consumed: {}", event);
    }

    @KafkaListener(
            topics = "${spring.kafka.topic.name}"
            , groupId = "car-delete"
    )
    public void consume(CarDeletedEvent event) {
        service.delete(event.getCarId());
        LOGGER.info("Car deleted event consumed: {}", event);
    }

    @KafkaListener(
            topics = "${spring.kafka.topic.name}"
            , groupId = "car-update"
    )
    public void consume(CarUpdatedEvent event) {
        Filter filter = mapper.forRequest().map(event, Filter.class);
        String id = service.getByCarId(event.getCarId()).getId();
        filter.setId(id);
        service.save(filter);
        LOGGER.info("Car updated event consumed: {}", event);
    }

    @KafkaListener(
            topics = "${spring.kafka.topic.name}"
            , groupId = "brand-delete"
    )
    public void consume(BrandDeletedEvent event) {
        service.deleteAllByBrandId(event.getBrandId());

        LOGGER.info("Brand deleted event consumed: {}", event);
    }

    @KafkaListener(
            topics = "${spring.kafka.topic.name}"
            , groupId = "brand-update"
    )
    public void consume(BrandUpdatedEvent event) {
        service.getByBrandId(event.getId()).forEach(filter -> {
            filter.setBrandName(event.getName());
            service.save(filter);
        });

        LOGGER.info("Brand updated event consumed: {}", event);
    }

    @KafkaListener(
            topics = "${spring.kafka.topic.name}"
            , groupId = "model-delete"
    )
    public void consume(ModelDeletedEvent event) {
        service.deleteAllByModelId(event.getModelId());

        LOGGER.info("Model deleted event consumed: {}", event);
    }

    @KafkaListener(
            topics = "${spring.kafka.topic.name}"
            , groupId = "model-update"
    )
    public void consume(ModelUpdatedEvent event) {
        service.getByModelId(event.getId()).forEach(filter -> {
            filter.setModelName(event.getName());
            filter.setBrandId(event.getBrandId());
            filter.setBrandName(service.getByBrandId(event.getBrandId()).get(0).getBrandName());
            service.save(filter);
        });

        LOGGER.info("Model updated event consumed: {}", event);
    }

    @KafkaListener(
            topics = "${spring.kafka.topic.name}"
            , groupId = "car-rental-create"
    )
    public void consume(CarRentalCreatedEvent event) {
        Filter filter = service.getByCarId(event.getCarId());
        filter.setState(3); // 3 = Rented
        service.save(filter);

        LOGGER.info("Car rental created event consumed: {}", event);
    }

    @KafkaListener(
            topics = "${spring.kafka.topic.name}"
            , groupId = "car-rental-update"
    )
    public void consume(CarRentalUpdatedEvent event) {
        Filter oldCar = service.getByCarId(event.getOldCarId());
        Filter newCar = service.getByCarId(event.getNewCarId());
        oldCar.setState(1); // 1 = Available
        newCar.setState(3); // 3 = Rented
        service.save(oldCar);
        service.save(newCar);

        LOGGER.info("Car rental updated event consumed: {}", event);
    }

    @KafkaListener(
            topics = "${spring.kafka.topic.name}"
            , groupId = "car-rental-delete"
    )
    public void consume(CarRentalDeletedEvent event) {
        Filter car = service.getByCarId(event.getCarId());
        car.setState(1); // 1 = Available
        service.save(car);
        LOGGER.info("Car rental deleted event consumed: {}", event);
    }
}
