package org.deb.account.exclusion;

import lombok.extern.slf4j.Slf4j;
import org.deb.account.exclusion.entity.ExclusionAccounts;
import org.deb.account.exclusion.repository.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("org.deb.account.exclusion.*")
@Slf4j
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner acccountSetup(AccountRepository accountRepository) {
        return args -> {
            accountRepository.save(new ExclusionAccounts("2000123458"));
            accountRepository.save(new ExclusionAccounts("5000123460"));
            accountRepository.save(new ExclusionAccounts("6000123462"));
        };
    }


}
