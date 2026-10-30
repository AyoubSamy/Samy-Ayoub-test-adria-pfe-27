package net.ayoub.accountservice.controllers.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.ayoub.accountservice.dto.AccountDTO;
import net.ayoub.accountservice.dto.AmountDTO;
import net.ayoub.accountservice.services.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountRestController {

    private final AccountService accountService;

    @GetMapping
    public List<AccountDTO> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/{id}")
    public AccountDTO getAccount(@PathVariable UUID id) {
        return accountService.getAccount(id);
    }

    @GetMapping("/{id}/balance")
    public double getBalance(@PathVariable UUID id) {
        return accountService.getBalance(id);
    }

    @PutMapping("/{id}/debit")
    public AccountDTO debit(@PathVariable UUID id, @Valid @RequestBody AmountDTO request) {
        return accountService.debit(id, request.getAmount());
    }

    @PutMapping("/{id}/credit")
    public AccountDTO credit(@PathVariable UUID id, @Valid @RequestBody AmountDTO request) {
        return accountService.credit(id, request.getAmount());
    }
}
