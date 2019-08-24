package com.moa.mobile.model.service;

import com.moa.mobile.model.dao.UserApplyDAO;
import com.moa.mobile.model.vo.ApplyListInfoVO;
import com.moa.mobile.model.vo.RequestListInfoVO;

import java.util.List;

public interface UserApplySearchService {
    List<ApplyListInfoVO> searchApplyList(int userId);
}
