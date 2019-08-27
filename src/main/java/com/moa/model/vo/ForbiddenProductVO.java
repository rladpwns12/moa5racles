package com.moa.model.vo;

import com.moa.valid.MaxByteLength;
import lombok.Data;

import javax.validation.constraints.NotBlank;
@Data
public class ForbiddenProductVO {
    @MaxByteLength(maxValue = 60, minValue = 1)
    private String category;
    @MaxByteLength(maxValue = 150, minValue = 1)
    private String product;
}
