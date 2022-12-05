package com.kodlamaio.rentalservice.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect // Aspect olduğunu belirtiyoruz.
public class RentalAspect {

    @Before("execution(* com.kodlamaio.rentalservice.business.concretes.RentalManager.add())") //Ne zaman ve nerde çalışacağını belirtiyoruz.
    public void beforeSave(JoinPoint joinPoint){
        System.out.println("Aspect Run"); // Ne çalışacağını belirtiyoruz.
        System.out.println(joinPoint.getSignature());
    }

}