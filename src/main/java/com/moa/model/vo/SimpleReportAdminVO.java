package com.moa.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.sql.Time;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimpleReportAdminVO {
    @NotBlank
    private long reportId;
    @NotBlank
    private String userNick;
    private Date reportDate;
    private Time reportTime;
}