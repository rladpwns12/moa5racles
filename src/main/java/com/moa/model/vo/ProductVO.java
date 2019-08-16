package com.moa.model.vo;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class ProductVO {
    @NotBlank
    @Min(1)
    @Max(20)
    private String category;
    @NotBlank
    @Min(1)
    @Max(50)
    private String product;
    @NotBlank
    @Min(0)
    @Max(99)
    private String productCnt;
}
