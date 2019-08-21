package com.moa.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleUserRequestVO {
	private int historyId;
	private int requestId;
	private UserAttachFileVO profile;
	private String hostNick;
	private Date startDate;
	private Date endDate;
	private List<String> productCategory = new ArrayList<String>();
	private List<String> productName = new ArrayList<String>();
	private List<Integer> productCnt = new ArrayList<Integer>();
	private int measuredPrice;
	private int bargainPrice;
	private int transactionPrice;
	private String baseAddress;
	private String detailAddress;
	private String transactionState;

}
