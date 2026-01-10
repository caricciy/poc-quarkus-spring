package br.com.a4a.subpay.application.service;

import br.com.a4a.subpay.application.port.inbound.GetAccountBalanceQuery;
import br.com.a4a.subpay.application.port.outbound.LoadAccountPort;
import br.com.a4a.subpay.domain.vo.AccountId;
import br.com.a4a.subpay.domain.vo.Money;

import java.time.LocalDateTime;

public record GetAccountBalanceService(LoadAccountPort loadAccount) implements GetAccountBalanceQuery {

    @Override
    public Money execute(AccountId accountId) {
        return loadAccount.loadAccount(accountId, LocalDateTime.now())
                .orElseThrow(() -> new IllegalArgumentException("Account not found"))
                .calculateBalance();
    }
}
