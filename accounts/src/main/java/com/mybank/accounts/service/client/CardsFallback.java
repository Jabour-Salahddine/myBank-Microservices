package com.mybank.accounts.service.client;


import com.mybank.accounts.dto.CardsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CardsFallback implements CardsFeignClient {

    @Override
    public ResponseEntity<CardsDto> fetchCardDetails(String correlationId, String mobileNumber) {
        // Return a default response or handle the fallback logic here
        return null; // Return an empty CardsDto or any default value
    }



}
