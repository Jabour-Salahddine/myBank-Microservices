package com.mybank.accounts.service.client;


import com.mybank.accounts.dto.CardsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("cards") // the name with it, the microservice is registered in eureka server
public interface CardsFeignClient {

    // the same method parameters as in CardsController in cards microservice, for the method name we can put any name
    @GetMapping(value = "/api/cards/fetch",consumes = "application/json")
    public ResponseEntity<CardsDto> fetchCardDetails(@RequestParam String mobileNumber);

}
