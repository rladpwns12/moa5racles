package com.moa.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@NoArgsConstructor
public class PayVO {
    @NotBlank
    @Min(1)
    private int historyId;
    @NotBlank
    private String merchantUid;
    @NotBlank
    private String impUid;
    @NotBlank
    private int transactionPrice;
    @NotBlank
    private String status;
    @NotBlank
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date depositDate;
}
