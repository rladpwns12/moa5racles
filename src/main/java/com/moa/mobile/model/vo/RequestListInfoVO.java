package com.moa.mobile.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestListInfoVO {
    private String date;
    private String time;
    private String nick;
    private String price;
}
