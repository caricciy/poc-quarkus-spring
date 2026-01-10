package br.com.a4a.subpay.application.port.inbound;


import br.com.a4a.subpay.domain.vo.AccountId;
import br.com.a4a.subpay.domain.vo.Money;
import br.com.a4a.subpay.shared.PositiveMoney;
import br.com.a4a.subpay.shared.SelfValidate;
import jakarta.validation.constraints.NotNull;


// Use case interface for viewing routers with filtering capability
// This use case is just a interface describing what the software does,
// it will be implemented by an input port.
public interface SendMoneyUseCase {

    boolean execute(SendMoneyCommand command);

    // Command encapsulating the details required to send money between accounts
    // the command class could be created outside of this interface
    record SendMoneyCommand(@NotNull AccountId sourceAccountId,
                            @NotNull AccountId targetAccountId,
                            @PositiveMoney Money money) implements SelfValidate<SendMoneyCommand> {
    }

    public static void main(String[] args) {
        SendMoneyCommand command = new SendMoneyCommand(AccountId.withoutId(),
                AccountId.withoutId(),
                Money.of(0));

        command.validate();
    }
}
