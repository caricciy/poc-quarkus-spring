package br.com.a4a.sbootadapter.config;

import br.com.a4a.sbootadapter.repository.AccountRepository;
import br.com.a4a.subpay.application.port.inbound.GetAccountBalanceQuery;
import br.com.a4a.subpay.application.service.GetAccountBalanceService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    GetAccountBalanceQuery getAccountBalanceService(AccountRepository accountRepository) {
        return new GetAccountBalanceService(accountRepository);
    }
}
