package br.com.a4a.subpay.domain.exception;

/**
 * Base class for all domain exceptions.
 * Represents business rule violations in the domain layer.
 */
public abstract class DomainException extends RuntimeException {

    protected DomainException(String message) {
        super(message);
    }

    protected DomainException(String message, Throwable cause) {
        super(message, cause);
    }
}

