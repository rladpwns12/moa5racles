package com.moa.mobile.model.service;

import com.moa.mobile.model.vo.RequestListInfoVO;

import java.util.List;

public interface HostRequestSearchService {
    List<RequestListInfoVO> searchRequestList(int hostId);
}
