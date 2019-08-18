package com.moa.model.service;

import com.moa.model.dao.StoreBoardDAOImpl;
import com.moa.model.vo.SimpleStorageBoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


interface MyStorageService {
	List<SimpleStorageBoardVO> selectMyStorage(Map<String,Object> storageInfo);

	int selectMyStorageCnt(int hostId);
}
