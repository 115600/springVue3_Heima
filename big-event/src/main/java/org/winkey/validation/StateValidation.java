package org.winkey.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.winkey.anno.State;

public class StateValidation implements ConstraintValidator<State  ,String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if(value == null) return false;
        if(value.equals("草稿") || value.equals("已发布"))
            return true;
        return false;
    }
}
