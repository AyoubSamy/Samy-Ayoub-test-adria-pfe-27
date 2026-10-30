package net.ayoub.transactionservice.controllers.graphql;

import lombok.RequiredArgsConstructor;
import net.ayoub.transactionservice.dto.TransactionDTO;
import net.ayoub.transactionservice.dto.TransactionRequestDTO;
import net.ayoub.transactionservice.dto.TransferRequestDTO;
import net.ayoub.transactionservice.services.TransactionService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class TransactionGraphQLController {

    private final TransactionService transactionService;

    @QueryMapping
    public List<TransactionDTO> transactions() {
        return transactionService.getAllTransactions();
    }

    @QueryMapping
    public List<TransactionDTO> transactionsByAccount(@Argument UUID accountId) {
        return transactionService.getTransactionsByAccount(accountId);
    }

    @MutationMapping
    public TransactionDTO createTransaction(@Argument TransactionRequestDTO request) {
        return transactionService.createTransaction(request);
    }

    @MutationMapping
    public boolean transfer(@Argument TransferRequestDTO request) {
        transactionService.transfer(request);
        return true;
    }
}
