package com.mybank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        name = "Customer",
        description = "Schema to hold Customer and Account information"
)
public class CustomerDto {

    @Schema(
            description = "Name of the customer", example = "john Doe"
    )
    @NotEmpty( message = "Customer name cannot be empty")
    @Size(min = 5, max = 30, message = "The length of the customer name should be between 5 and 30")
    private String name;

    @Schema(
            description = "Email of the customer", example = "example@gmail.com"
    )
    @NotEmpty( message = "Customer email cannot be empty")
    @Email(message = "Email address should be a valid value")
    private String email;

    @Schema(
            description = "MobileNumber of the customer", example = "0710025455"
    )
    @NotEmpty( message = "Customer mobile number cannot be empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    private AccountsDto accountsDto;  // we can have multiple accounts for a customer, so we can use List<AccountsDto> accountsDto; if needed


}