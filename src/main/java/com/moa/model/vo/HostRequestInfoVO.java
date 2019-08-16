package com.moa.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.lang.annotation.DeclareAnnotation;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HostRequestInfoVO {
    private String email;
    private String nick;
    private String name;
    private String phoneNumber;
    private String storageType;
}
