package net.ayoub.transactionservice.controllers.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.ayoub.transactionservice.dto.TransactionDTO;
import net.ayoub.transactionservice.dto.TransactionRequestDTO;
import net.ayoub.transactionservice.dto.TransferRequestDTO;
import net.ayoub.transactionservice.services.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
public class TransactionRestController {

    private final TransactionService transactionService;

    @GetMapping
    public List<TransactionDTO> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/account/{accountId}")
    public List<TransactionDTO> getTransactionsByAccount(@PathVariable UUID accountId) {
        return transactionService.getTransactionsByAccount(accountId);
    }

    @PostMapping
    public TransactionDTO createTransaction(@Valid @RequestBody TransactionRequestDTO request) {
        return transactionService.createTransaction(request);
    }

    @PostMapping("/transfer")
    public void transfer(@Valid @RequestBody TransferRequestDTO request) {
        transactionService.transfer(request);
    }
}
