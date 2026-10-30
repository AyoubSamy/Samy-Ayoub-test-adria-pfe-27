package net.ayoub.transactionservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class TransactionRequestDTO {
    @NotNull
    private UUID accountId;
    @NotNull @Positive
    private BigDecimal amount;
    @NotBlank
    private String currency;
    private String merchantCategory;
    private String country;
}
