package com.kodlamaio.common.events.inventory.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModelUpdatedEvent {
    private String id;
    private String name;
    private String brandId;
}
