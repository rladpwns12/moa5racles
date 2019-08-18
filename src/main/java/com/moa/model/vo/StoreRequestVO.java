package com.moa.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreRequestVO {
    @NotBlank
    private int storeRequestNum;
    @NotBlank
    private int articleNum;
    @NotBlank
    private int userId;
    @NotBlank
    private PriceVO price;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date startDate;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date endDate;
    @NotBlank
    private String transactionWay;
    @NotBlank
    @Max(1000)
    @Min(1)
    private String content;
    @NotBlank
    private List<ProductVO> productList;
    @NotBlank
    private List<Integer> productSize;
    @NotBlank
    private List<StoreRequestAttachFileVO> attachList;
}
