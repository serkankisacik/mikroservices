package com.kodlamaio.rentalservice.business.concretes;

import com.kodlamaio.common.constants.Messages;
import com.kodlamaio.common.dto.CreateRentalPaymentRequest;
import com.kodlamaio.common.events.payment.PaymentReceivedEvent;
import com.kodlamaio.common.events.rental.RentalCreatedEvent;
import com.kodlamaio.rentalservice.business.abstracts.RentalService;
import com.kodlamaio.rentalservice.business.requests.create.CreateRentalRequest;
import com.kodlamaio.rentalservice.business.requests.update.UpdateRentalRequest;
import com.kodlamaio.rentalservice.business.responses.create.CreateRentalResponse;
import com.kodlamaio.rentalservice.business.responses.get.GetAllRentalResponse;
import com.kodlamaio.rentalservice.business.responses.get.GetRentalResponse;
import com.kodlamaio.rentalservice.business.responses.update.UpdateRentalResponse;
import com.kodlamaio.rentalservice.client.CarClient;
import com.kodlamaio.rentalservice.client.PaymentClient;
import com.kodlamaio.rentalservice.dataAccess.abstracts.RentalRepository;
import com.kodlamaio.rentalservice.entities.Rental;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentalservice.kafka.RentalProducer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RentalManager implements RentalService {
    private final RentalRepository repository;
    private final ModelMapperService mapper;
    private RentalProducer rentalProducer;
    private CarClient carClient;
    private PaymentClient paymentClient;

    @Override
    public List<GetAllRentalResponse> getAll() {
        List<Rental> rentals = repository.findAll();
        List<GetAllRentalResponse> responses = rentals.stream().map(rental -> mapper.forResponse().map(rental, GetAllRentalResponse.class)).toList();
        return responses;
    }

    @Override
    public CreateRentalResponse add(CreateRentalRequest createRentalRequest) {
        carClient.checkIfCarAvailable(createRentalRequest.getCarId());
        Rental rental = mapper.forRequest().map(createRentalRequest, Rental.class);
        rental.setId(UUID.randomUUID().toString());
        rental.setDateStarted(LocalDateTime.now());
        double totalPrice = createRentalRequest.getRentedForDays() * createRentalRequest.getDailyPrice();
        rental.setTotalPrice(totalPrice);
        CreateRentalPaymentRequest paymentRequest = new CreateRentalPaymentRequest();
        mapper.forRequest().map(createRentalRequest.getPaymentRequest(), paymentRequest);
        paymentRequest.setPrice(totalPrice);

        paymentClient.checkIfPaymentSuccessful(paymentRequest);
        repository.save(rental);
        rentalCreatedEvent(rental);
        paymentReceivedEvent(createRentalRequest, rental, totalPrice);
        CreateRentalResponse response = mapper.forResponse().map(rental, CreateRentalResponse.class);

        return response;
    }

    @Override
    public GetRentalResponse getById(String id) {
        Rental rental = repository.findById(id).orElseThrow();
        GetRentalResponse response = mapper.forResponse().map(rental, GetRentalResponse.class);
        return response;
    }

    @Override
    public UpdateRentalResponse update(UpdateRentalRequest request, String id) {
        Rental rental = mapper.forRequest().map(request, Rental.class);
        rental.setId(id);
        repository.save(rental);
        UpdateRentalResponse response = mapper.forResponse().map(rental, UpdateRentalResponse.class);
        return response;
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    private void rentalCreatedEvent(Rental rental) {
        RentalCreatedEvent rentalCreatedEvent = new RentalCreatedEvent();
        rentalCreatedEvent.setCarId(rental.getCarId());
        rentalCreatedEvent.setMessage(Messages.Rental.Created);
        rentalProducer.sendMessage(rentalCreatedEvent);
    }

    private void paymentReceivedEvent(CreateRentalRequest request, Rental rental, double totalPrice) {
        PaymentReceivedEvent paymentReceivedEvent = new PaymentReceivedEvent();
        paymentReceivedEvent.setCarId(rental.getCarId());
        paymentReceivedEvent.setFullName(request.getPaymentRequest().getFullName());
        paymentReceivedEvent.setDailyPrice(request.getDailyPrice());
        paymentReceivedEvent.setTotalPrice(totalPrice);
        paymentReceivedEvent.setRentedForDays(request.getRentedForDays());
        paymentReceivedEvent.setRentedAt(rental.getDateStarted());
        paymentReceivedEvent.setBrandName(carClient.getCarResponse(rental.getCarId()).getBrandName());
        paymentReceivedEvent.setModelName(carClient.getCarResponse(rental.getCarId()).getModelName());
        paymentReceivedEvent.setModelYear(carClient.getCarResponse(rental.getCarId()).getModelYear());
        rentalProducer.sendMessage(paymentReceivedEvent);
    }
}
