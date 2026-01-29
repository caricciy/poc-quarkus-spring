package br.com.a4a.subpay.domain.entity;

import br.com.a4a.subpay.domain.vo.AccountId;
import br.com.a4a.subpay.domain.vo.ActivityWindow;
import br.com.a4a.subpay.domain.vo.Money;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Represents a payment account in the domain layer.
 * This class is a DDD (Domain-Driven Design) Entity with identity defined by AccountId.
 *
 * An Account maintains a baseline balance and tracks activities through an ActivityWindow.
 * The account state is immutable after creation, with activities being added to the window.
 *
 * @see AccountId
 * @see ActivityWindow
 * @see Activity
 */
public class Account {
    private final AccountId id;
    private final Money baselineBalance;
    private final ActivityWindow activityWindow;

    public Account(AccountId accountId, Money money, ActivityWindow activityWindow) {
        this.id = accountId;
        this.baselineBalance = money;
        this.activityWindow = activityWindow;
    }

    public Money calculateBalance() {
        return Money.add(this.baselineBalance, this.activityWindow.calculateBalance(this.id));
    }

    public boolean deposit(Money money, AccountId sourceAccountId) {
        var deposit = new Activity(this.id, sourceAccountId, this.id, LocalDateTime.now(), money);
        this.activityWindow.addActivity(deposit);
        return true;
    }

    public boolean withdraw(Money money, AccountId targetAccountId) {
        if (!mayWithdraw(money)) return false;
        var withdraw = new Activity(this.id, this.id, targetAccountId, LocalDateTime.now(), money);
        this.activityWindow.addActivity(withdraw);
        return true;
    }

    private boolean mayWithdraw(Money money) {
        return Money.subtract(this.calculateBalance(), money)
                .isPositive();
    }

    public Optional<AccountId> getId() {
        return Optional.of(this.id);
    }

    public static Account withoutId(Money baselineBalance, ActivityWindow activityWindow) {
        return new Account(null, baselineBalance, activityWindow);
    }

    public static Account withId(AccountId accountId, Money baselineBalance, ActivityWindow activityWindow) {
        return new Account(accountId, baselineBalance, activityWindow);
    }
}
