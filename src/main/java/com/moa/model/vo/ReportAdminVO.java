package com.moa.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Time;
import java.sql.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportAdminVO {
    @NotBlank
    private long reportId;
    @NotBlank
    private long userId;
    @NotBlank
    private String userNick;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    @Size(min = 0, max = 5)
    private String targetType;
    @NotBlank
    @Size(min = 1, max = 33)
    private String targetUserNick;
    @NotBlank
    @Size(min = 1, max = 1000)
    private String content;
    private Date reportDate;
    private Time reportTime;
    private boolean reportState;
}