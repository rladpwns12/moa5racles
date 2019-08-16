package com.moa.model.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PriceVO {
    @NotBlank
    private String selectPrice;
    @NotBlank
    private int measuredPrice;
    private int bargainPrice;
}
