package com.moa.mybatis.mobile;

import com.moa.mobile.model.vo.RequestListInfoVO;

import java.util.List;

public interface HostRequestMapper {
    List<RequestListInfoVO> hostRequestList(int hostId);
}
