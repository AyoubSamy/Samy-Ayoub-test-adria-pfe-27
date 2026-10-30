package net.ayoub.accountservice.dto;

import lombok.*;
import net.ayoub.accountservice.entities.AccountStatus;

import java.util.UUID;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AccountDTO {
    private UUID id;
    private String customerReference;
    private AccountStatus status;
    private String currency;
    private double balance;
}
