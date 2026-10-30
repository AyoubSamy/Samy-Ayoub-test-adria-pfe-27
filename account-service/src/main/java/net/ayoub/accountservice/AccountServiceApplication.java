package net.ayoub.accountservice;

import net.ayoub.accountservice.entities.Account;
import net.ayoub.accountservice.entities.AccountStatus;
import net.ayoub.accountservice.repository.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(AccountRepository accountRepository) {
        return args -> {
            accountRepository.save(Account.builder()
                    .customerReference("CUST-001")
                    .status(AccountStatus.ACTIVE)
                    .currency("EUR")
                    .balance(5000)
                    .build());
            accountRepository.save(Account.builder()
                    .customerReference("CUST-002")
                    .status(AccountStatus.ACTIVE)
                    .currency("MAD")
                    .balance(20000)
                    .build());
            accountRepository.save(Account.builder()
                    .customerReference("CUST-003")
                    .status(AccountStatus.BLOCKED)
                    .currency("EUR")
                    .balance(1000)
                    .build());

            accountRepository.findAll().forEach(account ->
                    System.out.println(account.getId() + " " + account.getCustomerReference()
                            + " " + account.getStatus() + " " + account.getBalance() + " " + account.getCurrency()));
        };
    }

}
