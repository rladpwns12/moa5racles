package com.moa.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class UserVO {
    @NotBlank
    @Size(min = 1, max = 60)
    @Email
    private String email;
    @NotBlank
    @Size(min = 5, max = 20)
    private String password;
    @NotBlank
    @Pattern(regexp = "/^\\d{3}-\\d{3,4}-\\d{4}$/")
    private String phoneNumber;
    @NotBlank
    @Size(min = 1, max = 20)
    private String nick;
    @NotBlank
    @Size(min = 1, max = 100)
    private String name;
    private String profile;
}
