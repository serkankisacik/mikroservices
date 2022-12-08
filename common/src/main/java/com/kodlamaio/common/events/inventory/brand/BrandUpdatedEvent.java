package com.kodlamaio.common.events.inventory.brand;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandUpdatedEvent {
    private String id;
    private String name;
}
