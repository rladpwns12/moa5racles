package com.moa.mybatis.mobile;

import com.moa.mobile.model.vo.ApplyListInfoVO;
import com.moa.mobile.model.vo.RequestListInfoVO;

import java.util.List;

public interface UserApplyMapper {
    List<ApplyListInfoVO> userApplyList(int userId);
}
