package br.com.a4a.subpay.domain.vo;

import java.math.BigDecimal;

/**
 * Represents a monetary amount in the domain layer.
 * This is a DDD (Domain-Driven Design) Value Object that is immutable.
 *
 * Uses BigDecimal internally to ensure precision in financial calculations
 * and avoid floating-point arithmetic issues.
 *
 * All operations return new Money instances, maintaining immutability.
 * Provides convenience methods for comparison and arithmetic operations.
 */
public class Money {

    private final BigDecimal amount;

    public static final Money ZERO = Money.of(0L);

    public Money(BigDecimal amount) {
        this.amount = amount;
    }

    public static Money of(long value) {
        return new Money(BigDecimal.valueOf(value));
    }

    public static Money add(Money a, Money b) {
        return new Money(a.amount.add(b.amount));
    }

    public static Money subtract(Money a, Money b) {
        return new Money(a.amount.subtract(b.amount));
    }

    public boolean isPositiveOrZero() {
        return amount.compareTo(BigDecimal.ZERO) >= 0;
    }

    public boolean isNegative() {
        return this.amount.compareTo(BigDecimal.ZERO) < 0;
    }

    public boolean isPositive() {
        return this.amount.compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean isGreaterThanOrEqualTo(Money money) {
        return this.amount.compareTo(money.amount) >= 0;
    }

    public boolean isGreaterThan(Money money) {
        return this.amount.compareTo(money.amount) > 0;
    }

    public Money minus(Money money) {
        return new Money(this.amount.subtract(money.amount));
    }

    public Money plus(Money money) {
        return new Money(this.amount.add(money.amount));
    }

    public Money negate() {
        return new Money(amount.negate());
    }

    public BigDecimal getAmount() {
        return this.amount;
    }
}
