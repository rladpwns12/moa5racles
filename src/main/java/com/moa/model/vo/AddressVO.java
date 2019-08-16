package com.moa.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class AddressVO {
    @NotBlank
    @Size(min = 1, max = 200)
    private String baseAddress;
    @NotBlank
    @Size(min = 1, max = 200)
    private String detailAddress;
    @NotBlank
    @Size(min = 1, max = 10)
    private String postCode;
    @NotBlank
    private Double lat;
    @NotBlank
    private Double lng;
}
