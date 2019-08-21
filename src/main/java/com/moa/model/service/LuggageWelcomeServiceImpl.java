package com.moa.model.service;

import com.moa.model.dao.StoreBoardDAO;
import com.moa.model.vo.StoreBoardFormVO;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@NoArgsConstructor
public class LuggageWelcomeServiceImpl implements LuggageWelcomeService {
    @Autowired
    private StoreBoardDAO storeBoardDAO;

    @Override
    public Map<String, Object> initBoard(String hostId) {
        Map<String, Object> map = new HashMap<>();
        map.put("storageAddress",storeBoardDAO.selectStorageAddress(hostId));
        List<String> transactionList = new ArrayList<>();
        map.put("transaction", storeBoardDAO.selectTransaction());
        List<String> categoryList = new ArrayList<>();
        map.put("category", storeBoardDAO.selectCategory());
        List<String> periodList = new ArrayList<>();
        map.put("period", storeBoardDAO.selectStoragePeriod());
        List<String> priceList = new ArrayList<>();
        map.put("price", storeBoardDAO.selectPrice());
        return map;
    }
    @Transactional
    @Override
    public void noticeStorage(StoreBoardFormVO storeBoardFormVO) {
        storeBoardDAO.insert(storeBoardFormVO);
    }
}
