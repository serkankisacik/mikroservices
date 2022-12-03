package com.kodlamaio.rentalservice.api.controllers;

import com.kodlamaio.rentalservice.business.abstracts.RentalService;
import com.kodlamaio.rentalservice.business.requests.create.CreateRentalRequest;
import com.kodlamaio.rentalservice.business.requests.update.UpdateRentalRequest;
import com.kodlamaio.rentalservice.business.responses.create.CreateRentalResponse;
import com.kodlamaio.rentalservice.business.responses.get.GetAllRentalResponse;
import com.kodlamaio.rentalservice.business.responses.get.GetRentalResponse;
import com.kodlamaio.rentalservice.business.responses.update.UpdateRentalResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rentals")
public class RentalController {
    private final RentalService rentalService;
    Logger logger = LoggerFactory.getLogger(RentalController.class);

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping
    public List<GetAllRentalResponse> getAll() {
        logger.info("Getting all rentals");
        return rentalService.getAll();
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public CreateRentalResponse add(@RequestBody CreateRentalRequest request) {
        logger.info("Adding new rental");
        return rentalService.add(request);
    }

    @PutMapping("/{id}")
    public UpdateRentalResponse update(@RequestBody UpdateRentalRequest updateRentalRequest, @PathVariable String id) {
        return rentalService.update(updateRentalRequest, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        rentalService.delete(id);
    }

    @GetMapping("/{id}")
    public GetRentalResponse getById(@PathVariable String id) {
        return rentalService.getById(id);
    }


}
