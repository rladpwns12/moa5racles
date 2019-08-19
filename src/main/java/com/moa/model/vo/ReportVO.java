package com.moa.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportVO {
    @NotBlank
    private Long userId;
    @NotBlank
    @Size(min = 0, max = 5)
    private int targetType;
    @NotBlank
    private Long targetId;
    @NotBlank
    @Size(min = 1, max = 33)
    private String targetUserNick;
    @NotBlank
    @Size(min = 1, max = 1000)
    private String content;
    private Date reportTime;
    private boolean reportState;
}
