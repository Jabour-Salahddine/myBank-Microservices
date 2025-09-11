package com.mybank.accounts.service.client;


import com.mybank.accounts.dto.CardsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="cards", fallback = CardsFallback.class) // the name with it, the microservice is registered in eureka server
public interface CardsFeignClient {

    // the same method parameters as in CardsController in cards microservice, for the method name we can put any name
    @GetMapping(value = "/api/cards/fetch",consumes = "application/json")
    public ResponseEntity<CardsDto> fetchCardDetails(@RequestHeader("mybank-correlation-id")
                                                         String correlationId,@RequestParam String mobileNumber);

}
