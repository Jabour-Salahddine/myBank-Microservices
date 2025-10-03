package com.mybank.eventsender.dto;

public record AccountsMsgDto(  // final class with immutable fields
        String mobileNumber,
        String name,
        String email,
        Long accountNumber
) { }
