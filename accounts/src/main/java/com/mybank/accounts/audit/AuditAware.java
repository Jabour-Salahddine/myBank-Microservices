
/*
*  cette classe est utilisée pour fournir l'utilisateur actuel qui effectue l'audit des entités
*  elle implémente l'interface AuditorAware de Spring Data JPA, qui est utilisée pour obtenir l'utilisateur actuel
*  la méthode getCurrentAuditor() retourne un Optional contenant le nom de l'utilisateur actuel, ici "ms_accounts"
*  cette logique peut être étendue pour obtenir l'utilisateur actuel à partir du contexte de sécurité ou d'une autre source d'authentification
*
*  cette classe est utilisé dans le contexte de l'audit JPA pour fournir des informations sur l'utilisateur qui effectue les modifications sur les entités qui représentent meda data comme cellle de la classe BaseEntity
*
*  les endroits où on a un code liés to audit sont :
*  *  - dans la classe BaseEntity, qui est une superclasse mappée pour les entités JPA, où les champs createdAt, createdBy, updatedAt et updatedBy sont définis
*  *  - dans la classe AccountsApplication, où l'audit JPA est activé avec l'annotation @EnableJpaAuditing et le bean "AuditAware" est spécifié pour fournir les informations sur l'auditeur
*  *  - cette classe est annotée avec @Component pour que Spring puisse la détecter et l'injecter là où elle est nécessaire
*
*
* */






package com.mybank.accounts.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("AuditAware") // Cette annotation permet à Spring de détecter cette classe comme un composant, ce qui est nécessaire pour l'injection de dépendances
public class AuditAware  implements AuditorAware<String> {


    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("ms_accounts"); // Remplacez "ms_accounts" par la logique pour obtenir l'utilisateur actuel, la chose qui sera réaliser lorsque l'on aura une authentification
    }

}
