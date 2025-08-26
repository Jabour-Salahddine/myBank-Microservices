package com.mybank.accounts.repository;

import com.mybank.accounts.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {


    Optional<Accounts> findByCustomerId(Long customerId);

    @Transactional  // This annotation is needed for modifying queries
    @Modifying     // This annotation indicates that the method will modify the database
    void deleteByCustomerId(Long customerId);   // we are telling Spring that this method will make changes in the database, soo please use a transaction


}
