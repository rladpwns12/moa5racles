package com.moa.model.service;

import com.moa.model.vo.StoreBoardFormVO;

import java.util.Map;

public interface LuggageWelcomeService {
    Map<String, Object> initBoard(String hostId);
    void noticeStorage(StoreBoardFormVO storeBoardFormVO);
}
