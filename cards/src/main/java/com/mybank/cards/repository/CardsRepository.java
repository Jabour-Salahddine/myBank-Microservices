package com.mybank.cards.repository;


import com.mybank.cards.entity.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardsRepository extends JpaRepository<Cards, Long> {

    Optional<Cards> findByMobileNumber(String mobileNumber);  // find card by mobile number wish is unique

    Optional<Cards> findByCardNumber(String cardNumber); // find card by card number which is unique

}