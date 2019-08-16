package com.moa.model.service;

import com.moa.model.dao.StoreRequestDAOImpl;
import com.moa.model.vo.SimpleHostRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

interface LuggageReceiveRecordService {
	List<SimpleHostRequestVO> selectLuggageWaitingReceiveRecord(Map<String,Object> listInfo);
	int selectLuggageWaitingReceiveRecordCnt(Map<String,Object> cntInfo);
}
