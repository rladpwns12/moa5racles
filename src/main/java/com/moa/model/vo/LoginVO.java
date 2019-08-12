package com.moa.model.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginVO {
    private String userId;
    private String nick;
    private String name;
    private String email;
    private String phoneNumber;
    private String profile;
}
