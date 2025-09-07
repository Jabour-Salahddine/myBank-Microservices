package com.mybank.accounts.service.impl;

import com.mybank.accounts.constants.AccountsConstants;
import com.mybank.accounts.dto.AccountsDto;
import com.mybank.accounts.dto.CustomerDto;
import com.mybank.accounts.entity.Accounts;
import com.mybank.accounts.entity.Customer;
import com.mybank.accounts.exception.CustomerAlreadyExistsException;
import com.mybank.accounts.exception.ResourceNotFoundException;
import com.mybank.accounts.mapper.AccountsMapper;
import com.mybank.accounts.mapper.CustomerMapper;
import com.mybank.accounts.repository.AccountsRepository;
import com.mybank.accounts.repository.CustomerRepository;
import com.mybank.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor // because we have juste one constructor, no need to write it, it's automatically generated with arguments that we have in the class
public class AccountsServiceImpl implements IAccountsService {

    private  AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;



    /**
     * This method creates a new account for the customer.
     * It checks if the customer already exists based on the mobile number.
     * If the customer does not exist, it creates a new customer and a new account.
     *
     * @param customerDto - CustomerDto Object
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer  = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> existingCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());

        if(existingCustomer.isPresent()) {
            // Handle the case where the customer already exists
            throw new CustomerAlreadyExistsException(
                "Customer with mobile number " + customerDto.getMobileNumber() + " already exists."
            );
        }

        customerRepository.save(customer);
        accountsRepository.save(createNewAccount(customer));

    }


    /**
     * @param customer - Customer Object
     * @return the new account details
     */
    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        return newAccount;
    }


    /**
     * This method fetches the account details for a given mobile number.
     * It retrieves the customer based on the mobile number and then fetches the associated account details.
     *
     * @param mobileNumber - Input Mobile Number
     * @return CustomerDto containing customer and account details
     */
    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
      Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer","mobileNumber", mobileNumber));

      Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Accounts", "customerId", customer.getCustomerId().toString()));


      CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer,new CustomerDto());
      customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

      return  customerDto;
    }



    /**
     * @param customerDto - CustomerDto Object
     * @return boolean indicating if the update of Account details is successful or not
     */
    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if(accountsDto !=null ){
            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString())
            );
            AccountsMapper.mapToAccounts(accountsDto, accounts);
            accounts = accountsRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
            );
            CustomerMapper.mapToCustomer(customerDto,customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return  isUpdated;
    }



    /**
     * @param mobileNumber - Input Mobile Number
     * @return boolean indicating if the delete of Account details is successful or not
     */
    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }


    @Override
    public String testService() {
        return "Accounts Service is up and running!";
    }


}
