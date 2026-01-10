package br.com.a4a.subpay.shared;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PositiveMoneyValidator.class)
@Documented
public @interface PositiveMoney {
    String message() default "must be non null and greater than zero";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}