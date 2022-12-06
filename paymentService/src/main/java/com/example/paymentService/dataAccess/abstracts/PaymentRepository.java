package com.example.paymentService.dataAccess.abstracts;

import com.example.paymentService.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, String> {

}
