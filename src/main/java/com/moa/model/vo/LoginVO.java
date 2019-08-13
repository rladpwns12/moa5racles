package com.moa.model.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginVO {
    private String userId;
    private String password;
    private String nick;
    private String name;
    private String email;
    private String phoneNumber;
    private String profile;
    private String flag;

    private List<AuthVO> authVOList;


}
