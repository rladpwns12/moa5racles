package com.moa.mobile.model.service;

import com.moa.mobile.model.vo.RequestListInfo;

import java.util.List;

public interface HostRequestSearchService {
    List<RequestListInfo> searchRequestList(int hostId);
}
