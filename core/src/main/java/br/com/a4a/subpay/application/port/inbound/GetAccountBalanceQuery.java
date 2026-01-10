package br.com.a4a.subpay.application.port.inbound;

import br.com.a4a.subpay.domain.vo.AccountId;
import br.com.a4a.subpay.domain.vo.Money;

public interface GetAccountBalanceQuery {
    Money execute(AccountId accountId);
}
