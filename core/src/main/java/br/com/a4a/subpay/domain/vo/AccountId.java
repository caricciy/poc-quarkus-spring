package br.com.a4a.subpay.domain.vo;

import java.util.Objects;
import java.util.UUID;

// Represents the unique identifier for a Account
// AccountId is a DDD Value Object
// We could implement this class inside Account entity, but for consistency and reusability, we keep it separate
public class AccountId {

    private final UUID value;

    private AccountId(UUID value) {
        this.value = value;
    }

    // Factory method to reate a AccountId from a given string value
    public static AccountId withId(String value) {
        return new AccountId(UUID.fromString(value));
    }

    // Factory method to Generate a new unique AccountId
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
