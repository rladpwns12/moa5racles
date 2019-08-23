package com.moa.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class MaxByteLengthListValidator implements ConstraintValidator<MaxByteLengthList, List<String>> {
    private int max;
    private int min;
    public void initialize(MaxByteLengthList constraintAnnotation) {
        this.min = constraintAnnotation.minValue();
        this.max = constraintAnnotation.maxValue();
    }

    @Override
    public boolean isValid(List<String> list, ConstraintValidatorContext constraintValidatorContext) {
        if(list==null)
            return true;
        else
            for(String object :list){
                if(!(object.getBytes(StandardCharsets.UTF_8).length >= this.min && object.getBytes(StandardCharsets.UTF_8).length <= this.max))
                    return false;
            }
            return true;
    }
}
