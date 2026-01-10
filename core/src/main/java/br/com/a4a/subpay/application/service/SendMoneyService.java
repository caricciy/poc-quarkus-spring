package br.com.a4a.subpay.application.service;

import br.com.a4a.subpay.application.port.inbound.SendMoneyUseCase;
import br.com.a4a.subpay.application.port.outbound.LoadAccountPort;

public record SendMoneyService(LoadAccountPort loadAccountPort) implements SendMoneyUseCase {

    @Override
    public boolean execute(SendMoneyCommand command) {

        return true;
    }
}
