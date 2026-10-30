package net.ayoub.transactionservice.services;

import lombok.RequiredArgsConstructor;
import net.ayoub.transactionservice.clients.AccountRestClient;
import net.ayoub.transactionservice.dto.*;
import net.ayoub.transactionservice.entities.Transaction;
import net.ayoub.transactionservice.exceptions.InvalidTransactionException;
import net.ayoub.transactionservice.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRestClient accountRestClient;

    @Value("${bank.transaction.max-amount:1000}")
    private BigDecimal maxAmount;

    public TransactionDTO createTransaction(TransactionRequestDTO request) {
        checkAmount(request.getAmount());
        AccountDTO account = getActiveAccount(request.getAccountId());
        if (!account.getCurrency().equals(request.getCurrency())) {
            throw new InvalidTransactionException("Transaction currency must match account currency: " + account.getCurrency());
        }
        accountRestClient.getBalance(account.getId());

        Transaction transaction = transactionRepository.save(Transaction.builder()
                .accountId(request.getAccountId())
                .amount(request.getAmount())
                .currency(request.getCurrency())
                .merchantCategory(request.getMerchantCategory())
                .country(request.getCountry())
                .createdAt(Instant.now())
                .build());

        accountRestClient.debit(account.getId(), new AmountDTO(request.getAmount().doubleValue()));
        return toDTO(transaction);
    }

    public void transfer(TransferRequestDTO request) {
        checkAmount(request.getAmount());
        AccountDTO from = getActiveAccount(request.getFromAccountId());
        AccountDTO to = getActiveAccount(request.getToAccountId());
        if (!from.getCurrency().equals(to.getCurrency())) {
            throw new InvalidTransactionException("Both accounts must have the same currency");
        }
        accountRestClient.getBalance(from.getId());

        AmountDTO amount = new AmountDTO(request.getAmount().doubleValue());
        accountRestClient.debit(from.getId(), amount);
        accountRestClient.credit(to.getId(), amount);
    }

    public List<TransactionDTO> getAllTransactions() {
        return transactionRepository.findAll().stream().map(this::toDTO).toList();
    }

    public List<TransactionDTO> getTransactionsByAccount(UUID accountId) {
        return transactionRepository.findByAccountId(accountId).stream().map(this::toDTO).toList();
    }

    private void checkAmount(BigDecimal amount) {
        if (amount.compareTo(maxAmount) > 0) {
            throw new InvalidTransactionException("Amount exceeds the maximum allowed: " + maxAmount);
        }
    }

    private AccountDTO getActiveAccount(UUID accountId) {
        AccountDTO account = accountRestClient.getAccount(accountId);
        if (!"ACTIVE".equals(account.getStatus())) {
            throw new InvalidTransactionException("Account is not active: " + accountId);
        }
        return account;
    }

    private TransactionDTO toDTO(Transaction transaction) {
        return TransactionDTO.builder()
                .id(transaction.getId())
                .accountId(transaction.getAccountId())
                .amount(transaction.getAmount())
                .currency(transaction.getCurrency())
                .merchantCategory(transaction.getMerchantCategory())
                .country(transaction.getCountry())
                .createdAt(transaction.getCreatedAt())
                .build();
    }
}
