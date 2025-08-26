
            // In this class we can write serialization and deserialization logic
           // to convert these DTOs to and from JSON or other formats as needed, but by default json is supported by spring boot

package com.mybank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        name = "Accounts",
        description = "Schema to hold Account information"
)
public class AccountsDto {

    @Schema(
            description = "AccountNumber of the account", example = "1234567890"
    )
    @NotEmpty(message = "AccountNumber can not be empty")
    @Pattern(regexp="(^$|[0-9]{10})",message = "AccountNumber must be 10 digits")
    private Long accountNumber;

    @Schema(
            description = "AccountType", example = "Savings"
    )
    @NotEmpty(message = "AccountType can not be a null or empty")
    private String accountType;

    @Schema(
            description = "BranchAddress of the account", example = "123 Main St, City, Country"
    )
    @NotEmpty(message = "BranchAddress can not be a null or empty")
    private String branchAddress;

}