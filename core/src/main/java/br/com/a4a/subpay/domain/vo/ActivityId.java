package br.com.a4a.subpay.domain.vo;

import java.util.Objects;
import java.util.UUID;

public class ActivityId {

    private final UUID value;

    private ActivityId(UUID value) {
        this.value = value;
    }

    // Factory method to reate a AccountId from a given string value
    public static ActivityId withId(String value) {
        return new ActivityId(UUID.fromString(value));
    }

    // Factory method to Generate a new unique AccountId
    public static ActivityId withoutId() {
        return new ActivityId(UUID.randomUUID());
    }


    public UUID getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityId accountId = (ActivityId) o;
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
