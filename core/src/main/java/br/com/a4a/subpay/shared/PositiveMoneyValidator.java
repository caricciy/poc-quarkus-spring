package br.com.a4a.subpay.shared;

import br.com.a4a.subpay.domain.vo.Money;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PositiveMoneyValidator implements ConstraintValidator<PositiveMoney, Money> {
    
    @Override
    public boolean isValid(Money money, ConstraintValidatorContext context) {
        // Null values are considered invalid (may we could let other annotations handle nullability)
        if (money == null) {
            return false;
        }
        return money.isGreaterThan(Money.of(0));
    }
}