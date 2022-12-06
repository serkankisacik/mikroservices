package com.example.paymentService.api.controllers;

import com.example.paymentService.bussiness.abstracts.PaymentService;
import com.example.paymentService.bussiness.requests.create.CreatePaymentRequest;
import com.example.paymentService.bussiness.requests.get.PaymentRequest;
import com.example.paymentService.bussiness.requests.update.UpdatePaymentRequest;
import com.example.paymentService.bussiness.responses.create.CreatePaymentResponse;
import com.example.paymentService.bussiness.responses.get.GetAllPaymentResponse;
import com.example.paymentService.bussiness.responses.get.GetPaymentResponse;
import com.example.paymentService.bussiness.responses.update.UpdatePaymentResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/payments")
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping
    public List<GetAllPaymentResponse> getAll() {
        return paymentService.getAll();
    }

    @GetMapping("/{id}")
    public GetPaymentResponse getById(@PathVariable String id) {
        return paymentService.getById(id);
    }

    @PostMapping
    public CreatePaymentResponse add(@Valid @RequestBody CreatePaymentRequest request) {
        return paymentService.add(request);
    }

    @PutMapping("/{id}")
    public UpdatePaymentResponse update(@Valid @RequestBody UpdatePaymentRequest request, @PathVariable String id) {
        return paymentService.update(request, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        paymentService.delete(id);
    }

    @PostMapping("/check")
    public void checkIfPaymentSuccessful(
            @RequestParam String cardNumber,
            @RequestParam String fullName,
            @RequestParam int cardExpirationYear,
            @RequestParam int cardExpirationMonth,
            @RequestParam String cardCvv,
            @RequestParam double price) {
        PaymentRequest request =
                new PaymentRequest(cardNumber,
                        fullName,
                        cardExpirationYear,
                        cardExpirationMonth,
                        cardCvv,
                        price);
        paymentService.checkIfPaymentSuccessful(request);
    }
}
