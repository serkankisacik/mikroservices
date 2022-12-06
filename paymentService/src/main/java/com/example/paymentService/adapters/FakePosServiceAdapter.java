package com.example.paymentService.adapters;

import com.example.paymentService.bussiness.abstracts.PosService;
import com.kodlamaio.common.utilities.exceptions.BusinessException;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class FakePosServiceAdapter implements PosService {
    @Override
    public void pay() {
        int randomNumber = new Random().nextInt(2);
        if (randomNumber == 1) {
            throw new BusinessException("PAYMENT_FAILED");
        }
    }

}
