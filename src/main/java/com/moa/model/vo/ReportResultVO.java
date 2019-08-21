package com.moa.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportResultVO {
    @NotBlank
    @NotBlank
    @Size(min = 1, max = 1000)
    String content;
    private Date resultTime;

}
