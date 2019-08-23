package com.moa.mobile.model.dao;

import com.moa.mobile.model.vo.ApplyListInfoVO;
import com.moa.mobile.model.vo.RequestListInfoVO;

import java.util.List;

public interface UserApplyDAO {
    List<ApplyListInfoVO> userApplyList(int userId);
}
