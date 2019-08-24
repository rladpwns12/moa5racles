package com.moa.mobile.model.dao;

import com.moa.mobile.model.vo.RequestListInfoVO;

import java.util.List;

public interface HostRequestDAO {
    List<RequestListInfoVO> requestList(int hostId);
}
