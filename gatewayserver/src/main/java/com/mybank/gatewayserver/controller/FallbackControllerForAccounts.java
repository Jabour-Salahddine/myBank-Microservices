package com.mybank.gatewayserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

public class FallbackControllerForAccounts {

    @RequestMapping("/mybank/fallback/accounts")
    public Mono<String> contactSupport() {
        return Mono.just("Error. Please try after some time or contact the support");
    }
}
