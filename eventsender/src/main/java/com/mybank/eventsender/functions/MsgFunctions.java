package com.mybank.eventsender.functions;

import com.mybank.eventsender.dto.AccountsMsgDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class MsgFunctions {

    private static final Logger logger = LoggerFactory.getLogger(MsgFunctions.class);


    /*
    * Le concept derrière

     On écrit notre métier sous forme de petites fonctions réutilisables.

      Ces fonctions sont :

            indépendantes (faciles à tester),

            exposables partout (REST, messaging, cloud).

Ici, la fonction email simule une étape dans un pipeline event-driven.

      Tu pourrais imaginer :

             Une fonction sms pour envoyer des SMS,

             Une fonction notifyAccounting pour alerter un service comptable,

Et Spring Cloud Function permet de chaîner ou router ces fonctions.
    *
    *
    *
    * */

    @Bean
    public Function<AccountsMsgDto,AccountsMsgDto> email ()
    {
        return accountsMsgDto -> {
            logger.info("email function called for account: {}", accountsMsgDto.accountNumber());
            // Simulate sending an email
            logger.info("Sending email to {} at {}", accountsMsgDto.name(), accountsMsgDto.email());
            // Return the original message for further processing
            return accountsMsgDto;
        };
    }

    @Bean
    public Function<AccountsMsgDto,String> sms ()
    {
        return accountsMsgDto -> {
            logger.info("sms function called for account: {}", accountsMsgDto.accountNumber());
            // Simulate sending an email
            logger.info("Sending sms to {} at {}", accountsMsgDto.name(), accountsMsgDto.mobileNumber());
            // Return the original message for further processing
            return accountsMsgDto.mobileNumber(); // Just return the mobile number as a simple response, cause for know we don't have the account number
        };
    }






}
