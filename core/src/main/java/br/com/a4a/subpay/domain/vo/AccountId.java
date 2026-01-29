package br.com.a4a.subpay.domain.vo;

import java.util.Objects;
import java.util.UUID;

/**
 * Represents the unique identifier for an Account.
 * This is a DDD (Domain-Driven Design) Value Object.
 *
 * We could implement this class inside the Account entity, but for consistency,
 * reusability, and to follow the Single Responsibility Principle, we keep it separate.
 *
 * Uses UUID internally to ensure global uniqueness across distributed systems.
 * The class is immutable and implements equals/hashCode based on the UUID value.
 *
 * @see Account
 */
public class AccountId {

    private final UUID value;

    private AccountId(UUID value) {
        this.value = value;
    }

    /**
     * Factory method to create an AccountId from a given string value.
     *
     * @param value the UUID string representation
     * @return a new AccountId instance
     * @throws IllegalArgumentException if the string is not a valid UUID
     */
    public static AccountId withId(String value) {
        return new AccountId(UUID.fromString(value));
    }

    /**
     * Factory method to generate a new unique AccountId.
     *
     * @return a new AccountId instance with a randomly generated UUID
     */
    public static AccountId withoutId() {
        return new AccountId(UUID.randomUUID());
    }


    public UUID getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountId accountId = (AccountId) o;
        return Objects.equals(value, accountId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
