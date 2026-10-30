package net.ayoub.transactionservice.clients;

import feign.FeignException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import net.ayoub.transactionservice.dto.AccountDTO;
import net.ayoub.transactionservice.dto.AmountDTO;
import net.ayoub.transactionservice.exceptions.AccountNotFoundException;
import net.ayoub.transactionservice.exceptions.AccountServiceUnavailableException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@FeignClient(name = "account-service")
public interface AccountRestClient {

    @GetMapping("/accounts/{id}")
    @CircuitBreaker(name = "accountService", fallbackMethod = "getAccountFallback")
    AccountDTO getAccount(@PathVariable UUID id);

    @GetMapping("/accounts/{id}/balance")
    @CircuitBreaker(name = "accountService", fallbackMethod = "getBalanceFallback")
    double getBalance(@PathVariable UUID id);

    @PutMapping("/accounts/{id}/debit")
    @CircuitBreaker(name = "accountService", fallbackMethod = "updateBalanceFallback")
    AccountDTO debit(@PathVariable UUID id, @RequestBody AmountDTO amount);

    @PutMapping("/accounts/{id}/credit")
    @CircuitBreaker(name = "accountService", fallbackMethod = "updateBalanceFallback")
    AccountDTO credit(@PathVariable UUID id, @RequestBody AmountDTO amount);

    default AccountDTO getAccountFallback(UUID id, Throwable e) {
        throw toBusinessException(id, e);
    }

    default double getBalanceFallback(UUID id, Throwable e) {
        throw toBusinessException(id, e);
    }

    default AccountDTO updateBalanceFallback(UUID id, AmountDTO amount, Throwable e) {
        throw toBusinessException(id, e);
    }

    private RuntimeException toBusinessException(UUID id, Throwable e) {
        if (e instanceof FeignException.NotFound) {
            return new AccountNotFoundException(id);
        }
        return new AccountServiceUnavailableException();
    }
}
