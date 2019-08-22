package com.moa.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.nio.charset.StandardCharsets;

public class MaxByteLengthValidator implements ConstraintValidator<MaxByteLength,String> {
    private int max;
    public void initialize(MaxByteLength constraintAnnotation) {
        this.max = constraintAnnotation.value();
    }
    @Override
    public boolean isValid(String object, ConstraintValidatorContext constraintContext) {
        return object == null || object.getBytes(StandardCharsets.UTF_8).length <= this.max;
    }
}
