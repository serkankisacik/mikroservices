package com.example.inventoryService.dataAccess.abstracts;

import com.example.inventoryService.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car,String> {
}
