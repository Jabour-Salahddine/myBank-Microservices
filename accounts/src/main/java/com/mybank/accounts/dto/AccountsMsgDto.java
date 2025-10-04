package com.mybank.accounts.dto;


public record AccountsMsgDto(  // final class with immutable fields
                               String mobileNumber,
                               String name,
                               String email,
                               Long accountNumber
) { }
