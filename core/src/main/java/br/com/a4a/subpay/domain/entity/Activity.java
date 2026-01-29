package br.com.a4a.subpay.domain.entity;

import br.com.a4a.subpay.domain.vo.AccountId;
import br.com.a4a.subpay.domain.vo.ActivityId;
import br.com.a4a.subpay.domain.vo.Money;

import java.time.LocalDateTime;

/**
 * Represents a financial transaction activity between accounts.
 * This class is a DDD (Domain-Driven Design) Entity that tracks money transfers.
 *
 * Each activity has:
 * - An owner account (the account that holds this activity in its history)
 * - A source account (where the money comes from)
 * - A target account (where the money goes to)
 * - A timestamp (when the activity occurred)
 * - An amount (how much money was transferred)
 *
 * @see ActivityId
 * @see AccountId
 * @see Money
 */
public class Activity {

    private final ActivityId id;

    private final AccountId ownerAccountId;
    private final AccountId sourceAccountId;
    private final AccountId targetAccountId;
    private final LocalDateTime timestamp;
    private final Money money;

    public Activity(AccountId ownerAccountId, AccountId sourceAccountId, AccountId targetAccountId, LocalDateTime timestamp, Money money) {
        this.id = null;
        this.ownerAccountId = ownerAccountId;
        this.sourceAccountId = sourceAccountId;
        this.targetAccountId = targetAccountId;
        this.timestamp = timestamp;
        this.money = money;
    }

    public ActivityId getId() {
        return id;
    }

    public AccountId getOwnerAccountId() {
        return ownerAccountId;
    }

    public AccountId getSourceAccountId() {
        return sourceAccountId;
    }

    public AccountId getTargetAccountId() {
        return targetAccountId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Money getMoney() {
        return money;
    }

}