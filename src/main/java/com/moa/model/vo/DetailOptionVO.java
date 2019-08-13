package com.moa.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailOptionVO {
	private ArrayList<String> category;
	private int distance;
	private String filter;
	private String storageType;
	private String transactionType;
	private String storagePeriodType;
	private String securityFacility;
	private String petFlag;
	private double latitude;
	private double longitude;

		
}
