package net.ayoub.accountservice.controllers.graphql;

import lombok.RequiredArgsConstructor;
import net.ayoub.accountservice.dto.AccountDTO;
import net.ayoub.accountservice.services.AccountService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class AccountGraphQLController {

    private final AccountService accountService;

    @QueryMapping
    public List<AccountDTO> accounts() {
        return accountService.getAllAccounts();
    }

    @QueryMapping
    public AccountDTO accountById(@Argument UUID id) {
        return accountService.getAccount(id);
    }

    @QueryMapping
    public double balance(@Argument UUID id) {
        return accountService.getBalance(id);
    }

    @MutationMapping
    public AccountDTO debit(@Argument UUID id, @Argument double amount) {
        return accountService.debit(id, amount);
    }

    @MutationMapping
    public AccountDTO credit(@Argument UUID id, @Argument double amount) {
        return accountService.credit(id, amount);
    }
}
