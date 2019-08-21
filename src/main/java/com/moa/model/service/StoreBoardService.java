package com.moa.model.service;

import com.moa.model.vo.DetailOptionVO;
import com.moa.model.vo.EntrustSearchVO;

import java.util.List;
import java.util.Map;

public interface StoreBoardService {
    public Map<String,Object> selectStorage(int articleNum);
    boolean deleteStorage(int articleNum);
}
