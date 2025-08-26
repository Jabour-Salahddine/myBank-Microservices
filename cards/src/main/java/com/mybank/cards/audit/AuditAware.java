package com.mybank.cards.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("AuditAware")
public class AuditAware implements AuditorAware<String> {

    /**
     * Returns the current auditor of the application.
     *
     * @return the current auditor.
     */

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("ms_cards"); // This can be replaced with actual logic to fetch the current user
    }

}