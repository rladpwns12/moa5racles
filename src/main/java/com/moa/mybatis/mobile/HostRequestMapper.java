package com.moa.mybatis.mobile;

import com.moa.mobile.model.vo.RequestListInfo;

import java.util.List;

public interface HostRequestMapper {
    List<RequestListInfo> HostRequestList(int hostId);
}
