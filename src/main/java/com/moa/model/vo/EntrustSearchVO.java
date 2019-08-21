package com.moa.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntrustSearchVO {
	private int articleNum;
	private int detailPrice;
	private double starPointAvg;
	private String storageType;
	private StoreBoardAttachFileVO profile;
	private int totReviewCnt;
	private String storagePeriodTypeId;
	private String nickName;
	private double latitude;
	private double longitude;
	private String distanceResult;
	

	
	
	
}
