package com.mybank.accounts.service;

import com.mybank.accounts.dto.CustomerDetailsDto;

public interface ICustomersService {

    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber);

}
