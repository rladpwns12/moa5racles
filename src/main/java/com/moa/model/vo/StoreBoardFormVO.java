package com.moa.model.vo;

import com.moa.valid.MaxByteLength;
import com.moa.valid.MaxByteLengthList;
import com.moa.valid.MaxList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreBoardFormVO {
    private Long articleNum;
    private String hostId;
    @NotNull
    @MaxByteLength(maxValue = 60)
    private String transactionType;
    @MaxByteLength(maxValue = 100)
    private String pet;
    @MaxByteLengthList(maxValue = 60)
    @MaxList(max = 100)
    @NotNull
    private List<String> securityList;
    @Valid
    @MaxList(max = 100)
    private List<ForbiddenProductVO> forbiddenProductList;
    @NotBlank
    @MaxByteLength(maxValue = 30)
    private String storagePeriodType;
    @MaxByteLengthList(maxValue = 7) //0~999만원
    @NotNull
    private List<String> detailPrice;
    @Valid
    @MaxList(min = 2, max = 6)
    @NotNull
    private List<StoreRequestAttachFileVO> attachList;
    @NotBlank
    @MaxByteLength(maxValue = 60)
    private String title;
    @NotNull
    @MaxByteLength(maxValue = 4000)
    private String content;
    @Min(0)
    private Long storageId;
}
