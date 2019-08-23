package com.moa.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.nio.charset.StandardCharsets;

public class MaxByteLengthValidator implements ConstraintValidator<MaxByteLength,String> {
    private int max;
    private int min;
    public void initialize(MaxByteLength constraintAnnotation) {
        this.min = constraintAnnotation.minValue();
        this.max = constraintAnnotation.maxValue();
    }
    @Override
    public boolean isValid(String object, ConstraintValidatorContext constraintContext) {
        if(object==null)
            return true;
        return object.getBytes(StandardCharsets.UTF_8).length >= this.min && object.getBytes(StandardCharsets.UTF_8).length <= this.max;
    }
}
