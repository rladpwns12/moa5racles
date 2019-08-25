package com.moa.mobile.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplyListInfoVO {
    private String date;
    private String time;
    private String nick;
    private String price;
    private String transactionType;
    private String baseAddress;
    private String detailAddress;
}
