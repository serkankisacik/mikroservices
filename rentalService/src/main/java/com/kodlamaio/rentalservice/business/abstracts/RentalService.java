package com.kodlamaio.rentalservice.business.abstracts;

import com.kodlamaio.rentalservice.business.requests.create.CreateRentalRequest;
import com.kodlamaio.rentalservice.business.requests.update.UpdateRentalRequest;
import com.kodlamaio.rentalservice.business.responses.create.CreateRentalResponse;
import com.kodlamaio.rentalservice.business.responses.get.GetAllRentalResponse;
import com.kodlamaio.rentalservice.business.responses.get.GetRentalResponse;
import com.kodlamaio.rentalservice.business.responses.update.UpdateRentalResponse;

import java.util.List;

public interface RentalService {

    List<GetAllRentalResponse> getAll();

    CreateRentalResponse add(CreateRentalRequest createBrandRequest);

    GetRentalResponse getById(String id);

    UpdateRentalResponse update(UpdateRentalRequest request, String id);
    void delete(String id);
}
