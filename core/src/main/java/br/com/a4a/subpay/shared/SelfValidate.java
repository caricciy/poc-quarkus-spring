package br.com.a4a.subpay.shared;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

// this class could be packaged in a library for reuse in other projects
public interface SelfValidate<T> {

    default void validate() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            Validator validator = factory.getValidator();
            var violations = validator.validate((T) this);
            if (!violations.isEmpty()) {
                var sb = new StringBuilder();
                for (var violation : violations) {
                    // may create a list of violations instead of a single string
                    sb.append(violation.getPropertyPath())
                            .append(" ")
                            .append(violation.getMessage())
                            .append(". ");
                }
                throw new IllegalArgumentException("Validation failed: " + sb);
            }
        }
    }
}
