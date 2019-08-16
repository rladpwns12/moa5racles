package com.moa.model.service;

import com.moa.model.dao.StoreBoardDAOImpl;
import com.moa.model.vo.SimpleStorageBoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MyStorageServiceImpl implements MyStorageService{
	@Autowired
	private StoreBoardDAOImpl storeBaordDAO;

	
	public List<SimpleStorageBoardVO> selectMyStorage(Map<String,Object> storageInfo){
		return storeBaordDAO.searchMyStorage(storageInfo);
	}

	public int selectMyStorageCnt(int hostId){
		return storeBaordDAO.searchMyStorageCnt(hostId);
	}
}
