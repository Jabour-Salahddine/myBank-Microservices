package com.mybank.accounts.service.client;


import com.mybank.accounts.dto.LoansDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


@Component
public class LoansFallback implements LoansFeignClient {

    @Override
    public ResponseEntity<LoansDto> fetchLoanDetails(String correlationId, String mobileNumber) {
        // Return a default response or handle the fallback logic here
        return null; // Return an empty LoansDto or any default value
    }

}
