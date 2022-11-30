package com.example.inventoryService.dataAccess.abstracts;

import com.example.inventoryService.entities.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model, String> {
    boolean existsByNameIgnoreCase(String name);
}
