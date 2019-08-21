package com.moa.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimpleStorageBoardVO {
	private int articleNum;
	private String title;
	private int storeProductCnt;
	private int expireProductCnt;
	private int favoriteCnt;
	private int reviewCnt;
	private double starPoint;
	private StoreBoardAttachFileVO boardAttach;

	

}
