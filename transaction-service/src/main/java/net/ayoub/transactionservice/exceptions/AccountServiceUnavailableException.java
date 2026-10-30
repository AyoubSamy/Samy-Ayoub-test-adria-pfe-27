package net.ayoub.transactionservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class AccountServiceUnavailableException extends RuntimeException {
    public AccountServiceUnavailableException() {
        super("account-service is unavailable, please try again later");
    }
}
