package com.example.paymentService.bussiness.concretes;

import com.example.paymentService.bussiness.abstracts.PaymentService;
import com.example.paymentService.bussiness.abstracts.PosService;
import com.example.paymentService.bussiness.requests.create.CreatePaymentRequest;
import com.example.paymentService.bussiness.requests.get.PaymentRequest;
import com.example.paymentService.bussiness.requests.update.UpdatePaymentRequest;
import com.example.paymentService.bussiness.responses.create.CreatePaymentResponse;
import com.example.paymentService.bussiness.responses.get.GetAllPaymentResponse;
import com.example.paymentService.bussiness.responses.get.GetPaymentResponse;
import com.example.paymentService.bussiness.responses.update.UpdatePaymentResponse;
import com.example.paymentService.dataAccess.abstracts.PaymentRepository;
import com.example.paymentService.entities.Payment;
import com.kodlamaio.common.utilities.exceptions.BusinessException;
import com.kodlamaio.common.utilities.mapping.ModelMapperService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PaymentManager implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final ModelMapperService mapper;
    private final PosService posService;

    @Override
    public List<GetAllPaymentResponse> getAll() {
        List<Payment> payments = paymentRepository.findAll();
        List<GetAllPaymentResponse> response = payments.stream().map(payment -> mapper.forResponse().map(payment, GetAllPaymentResponse.class)).toList();
        return response;
    }

    @Override
    public GetPaymentResponse getById(String id) {
        checkIfPaymentExists(id);
        Payment payment = paymentRepository.findById(id).orElseThrow();
        GetPaymentResponse response = mapper.forResponse().map(payment, GetPaymentResponse.class);
        return response;
    }

    @Override
    public CreatePaymentResponse add(CreatePaymentRequest createPaymentRequest) {
        Payment payment = mapper.forRequest().map(createPaymentRequest, Payment.class);
        payment.setId(UUID.randomUUID().toString());

        posService.pay();

        paymentRepository.save(payment);
        CreatePaymentResponse response = mapper.forResponse().map(payment, CreatePaymentResponse.class);
        return response;
    }

    @Override
    public UpdatePaymentResponse update(UpdatePaymentRequest request, String id) {
        checkIfPaymentExists(id);
        Payment payment = mapper.forRequest().map(request, Payment.class);
        payment.setId(id);
        paymentRepository.save(payment);
        UpdatePaymentResponse response = mapper.forResponse().map(payment, UpdatePaymentResponse.class);
        return response;
    }

    @Override
    public void delete(String id) {
        checkIfPaymentExists(id);
        paymentRepository.deleteById(id);
    }

    @Override
    public void checkIfPaymentSuccessful(PaymentRequest request) {

    }

    private void checkIfPaymentExists(String id) {
        if (!paymentRepository.existsById(id)) {
            throw new BusinessException("PAYMENT_NOT_FOUND!");
        }
    }
}
