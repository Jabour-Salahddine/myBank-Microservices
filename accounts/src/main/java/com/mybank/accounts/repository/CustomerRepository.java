package com.mybank.accounts.repository;

import com.mybank.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {


    Optional<Customer> findByMobileNumber(String mobileNumber);

    // Custom query methods can be defined here if needed
    // For example:
    // List<Customer> findByLastName(String lastName);

    // You can also use Spring Data JPA's derived query methods
    // or define custom queries using @Query annotation.
}
