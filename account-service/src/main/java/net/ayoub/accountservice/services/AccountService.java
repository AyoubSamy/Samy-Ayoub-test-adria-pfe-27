package net.ayoub.accountservice.services;

import lombok.RequiredArgsConstructor;
import net.ayoub.accountservice.dto.AccountDTO;
import net.ayoub.accountservice.entities.Account;
import net.ayoub.accountservice.exceptions.AccountNotFoundException;
import net.ayoub.accountservice.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public List<AccountDTO> getAllAccounts() {
        return accountRepository.findAll().stream().map(this::toDTO).toList();
    }

    public AccountDTO getAccount(UUID id) {
        return toDTO(findAccount(id));
    }

    public double getBalance(UUID id) {
        return findAccount(id).getBalance();
    }

    public AccountDTO debit(UUID id, double amount) {
        checkPositive(amount);
        Account account = findAccount(id);
        account.setBalance(account.getBalance() - amount);
        return toDTO(accountRepository.save(account));
    }

    public AccountDTO credit(UUID id, double amount) {
        checkPositive(amount);
        Account account = findAccount(id);
        account.setBalance(account.getBalance() + amount);
        return toDTO(accountRepository.save(account));
    }

    private void checkPositive(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be strictly positive");
        }
    }

    private Account findAccount(UUID id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));
    }

    private AccountDTO toDTO(Account account) {
        return AccountDTO.builder()
                .id(account.getId())
                .customerReference(account.getCustomerReference())
                .status(account.getStatus())
                .currency(account.getCurrency())
                .balance(account.getBalance())
                .build();
    }
}
