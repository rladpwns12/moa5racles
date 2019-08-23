package com.moa.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class MaxListValidator implements ConstraintValidator<MaxList, List<? extends Object>> {
    private int max;
    private int min;
    public void initialize(MaxList constraintAnnotation) {
        this.max = constraintAnnotation.max();
        this.min= constraintAnnotation.min();
    }

    @Override
    public boolean isValid(List<?> objects, ConstraintValidatorContext constraintValidatorContext) {
        if(objects==null)
            return true;
        return objects.size() >= this.max && objects.size() <= this.max;
    }
}
