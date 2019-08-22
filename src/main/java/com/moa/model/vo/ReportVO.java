package com.moa.model.vo;

import com.moa.valid.MaxByteLength;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportVO {
    private long reportId;
    @Min(0)
    private long userId;
    private String userNick;
    @Min(0)
    @Max(5)
    private int targetType;
    @Min(0)
    private long targetId;
    @NotBlank
    @MaxByteLength(100)
    private String targetUserNick;
    @NotBlank
    @MaxByteLength(3000)
    private String content;
    private Date reportTime;
    private boolean reportState;
}