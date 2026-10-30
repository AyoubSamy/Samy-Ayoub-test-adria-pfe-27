package net.ayoub.transactionservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class AccountDTO {
    private UUID id;
    private String customerReference;
    private String status;
    private String currency;
    private double balance;
}
