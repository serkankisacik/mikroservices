package com.example.paymentService.bussiness.abstracts;

import com.example.paymentService.bussiness.requests.create.CreatePaymentRequest;
import com.example.paymentService.bussiness.requests.get.PaymentRequest;
import com.example.paymentService.bussiness.requests.update.UpdatePaymentRequest;
import com.example.paymentService.bussiness.responses.create.CreatePaymentResponse;
import com.example.paymentService.bussiness.responses.get.GetAllPaymentResponse;
import com.example.paymentService.bussiness.responses.get.GetPaymentResponse;
import com.example.paymentService.bussiness.responses.update.UpdatePaymentResponse;
import com.kodlamaio.common.dto.CreateRentalPaymentRequest;

import java.util.List;

public interface PaymentService {
    List<GetAllPaymentResponse> getAll();
    GetPaymentResponse getById(String id);
    CreatePaymentResponse add(CreatePaymentRequest request);
    UpdatePaymentResponse update(UpdatePaymentRequest request, String id);
    void delete(String id);
    void checkIfPaymentSuccessful(CreateRentalPaymentRequest request);
}
