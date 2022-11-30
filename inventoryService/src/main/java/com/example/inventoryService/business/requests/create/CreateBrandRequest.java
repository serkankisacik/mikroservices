package com.example.inventoryService.business.requests.create;

import com.example.inventoryService.entities.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBrandRequest {
    @NotBlank
    @NotNull
    @Size(min = 2, max = 20)
    private String name;
}
