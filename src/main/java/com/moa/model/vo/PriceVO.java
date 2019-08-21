package com.moa.model.vo;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class PriceVO {
    @NotBlank
    private String selectPrice;
    @NotBlank
    @Min(0)
    private int measuredPrice;
    @NotBlank
    @Min(0)
    private int bargainPrice;
}
