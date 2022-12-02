package com.kodlamaio.rentalservice.client;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "carclient", url = "http://localhost:9010/stock/api/v1/")
public interface CarClient {
    @RequestMapping(method = RequestMethod.GET, value = "cars/checkIfCarAvailable/{id}")
    @Headers(value = "Content-Type: application/json")
    void checkIfCarAvailable(@PathVariable String id);
}
