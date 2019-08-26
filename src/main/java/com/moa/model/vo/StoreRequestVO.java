package com.moa.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.moa.valid.MaxByteLength;
import com.moa.valid.MaxList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreRequestVO {
    private int storeRequestNum;
    private int articleNum;
    private int userId;
    @Valid
    private PriceVO price;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date startDate;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date endDate;
    @NotNull
    @MaxByteLength(maxValue = 60)
    private String transactionWay;
    @NotNull
    @MaxByteLength(maxValue = 3000)
    private String content;
    @Valid
    @MaxList(min = 1, max = 100)
    @NotNull
    private List<ProductVO> productList;
    @NotNull
    private List<Integer> productSize;
    @Valid
    @MaxList(min = 2, max = 6)
    @NotNull
    private List<StoreRequestAttachFileVO> attachList;
}
