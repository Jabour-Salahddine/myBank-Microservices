package com.mybank.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass // cette annotation indique que cette classe est une superclasse mappée, ce qui signifie que ses champs seront hérités par les classes qui l'étendent, ainsi seront inclus dans la table de la classe enfant
@EntityListeners(AuditingEntityListener.class) // cette annotation permet à Spring Data JPA de gérer les événements d'audit, c'est-à-dire la création et la mise à jour des ces champs automatiquement

@Getter @Setter @ToString
public class BaseEntity {

        @CreatedDate  // cette annotation indique que ce champ sera automatiquement rempli avec la date et l'heure de création de l'entité grace à Spring Data JPA "auditingEntityListener"
        @Column(updatable = false)
        private LocalDateTime createdAt;

        @CreatedBy
        @Column(updatable = false)
        private String createdBy;

        @LastModifiedDate
        @Column(insertable = false)
        private LocalDateTime updatedAt;

        @LastModifiedBy
        @Column(insertable = false)
        private String updatedBy;


}

