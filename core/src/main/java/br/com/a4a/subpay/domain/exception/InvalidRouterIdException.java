package br.com.a4a.subpay.domain.exception;

/**
 * Exception thrown when a RouterId value is invalid.
 * Represents a violation of RouterId invariants in the domain.
 */
public class InvalidRouterIdException extends DomainException {

    public InvalidRouterIdException(String message) {
        super(message);
    }

    public InvalidRouterIdException(String message, Throwable cause) {
        super(message, cause);
    }
}

