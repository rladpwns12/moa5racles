package com.moa.model.service;

import com.moa.model.dao.StoreRequestDAOImpl;
import com.moa.model.vo.SimpleHostRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LuggageReceiveRecordServiceImpl {
	@Autowired
	private StoreRequestDAOImpl storeRequestDAO;

	public List<SimpleHostRequestVO> selectLuggageWaitingReceiveRecord(Map<String,Object> listInfo) {
		List<SimpleHostRequestVO> list = storeRequestDAO.searchListByHost(listInfo);

		return list;
	}

	public int selectLuggageWaitingReceiveRecordCnt(Map<String,Object> cntInfo){
		int cnt = storeRequestDAO.searchAllListCnt(cntInfo);

		return cnt;
	}
}
