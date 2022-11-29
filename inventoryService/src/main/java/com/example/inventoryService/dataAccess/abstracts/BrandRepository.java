package com.example.inventoryService.dataAccess.abstracts;

import com.example.inventoryService.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand,String> {
    Brand findByName(String name);
}
