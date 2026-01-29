package br.com.a4a.sbootadapter.repository;

import br.com.a4a.subpay.application.port.outbound.LoadAccountPort;
import br.com.a4a.subpay.domain.entity.Account;
import br.com.a4a.subpay.domain.vo.AccountId;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public class AccountRepository implements LoadAccountPort {

    @Override
    public Optional<Account> loadAccount(AccountId accountId, LocalDateTime baselineDate) {
        return Optional.empty();
    }
}
