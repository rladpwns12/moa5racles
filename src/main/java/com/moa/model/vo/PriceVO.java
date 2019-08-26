package com.moa.model.vo;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PriceVO {
    @NotNull
    private String selectPrice;
    @Min(0)
    private int measuredPrice;
    @Min(0)
    private int bargainPrice;
}
