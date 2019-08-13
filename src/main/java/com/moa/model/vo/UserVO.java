package com.moa.model.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class UserVO {
    private String email;
    private String password;
    private String phoneNumber;
    private String nick;
    private String name;
    private String profile;
}
