package br.com.a4a.subpay.domain.exception;

/**
 * Exception thrown when an Activity value is invalid.
 * Represents a violation of Activity invariants in the domain.
 */
public class InvalidActivityException extends DomainException {

    public InvalidActivityException(String message) {
        super(message);
    }

    public InvalidActivityException(String message, Throwable cause) {
        super(message, cause);
    }
}

