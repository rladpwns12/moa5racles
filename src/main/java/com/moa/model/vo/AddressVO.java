package com.moa.model.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class AddressVO {
    private String baseAddress;
    private String detailAddress;
    private String postCode;
    private Double lat;
    private Double lng;
}
