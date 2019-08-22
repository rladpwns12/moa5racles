package com.moa.mobile.model.dao;

import com.moa.mobile.model.vo.RequestListInfo;

import java.util.List;

public interface HostRequestDAO {
    List<RequestListInfo> requestList(int hostId);
}
