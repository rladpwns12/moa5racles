package com.moa.model.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
@Data
public class ForbiddenProductVO {
    @NotBlank
    private String category;
    @NotBlank
    private String product;
}
