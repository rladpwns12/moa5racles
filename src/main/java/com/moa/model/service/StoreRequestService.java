package com.moa.model.service;

import com.moa.model.vo.StoreRequestVO;

public interface StoreRequestService {
     boolean insert(StoreRequestVO storeRequestVO);
    boolean isOwnsBoard(int userId, int articleNum);
}
