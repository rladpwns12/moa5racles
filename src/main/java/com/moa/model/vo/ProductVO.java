package com.moa.model.vo;

import com.moa.valid.MaxByteLength;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ProductVO {
    @NotNull
    @MaxByteLength(maxValue = 60)
    private String category;
    @NotNull
    @MaxByteLength(maxValue = 150)
    private String product;
    @NotNull
    @MaxByteLength(maxValue = 10)
    private String productCnt;
}
