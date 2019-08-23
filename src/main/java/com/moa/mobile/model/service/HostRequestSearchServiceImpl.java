package com.moa.mobile.model.service;

import com.moa.mobile.model.dao.HostRequestDAO;
import com.moa.mobile.model.vo.RequestListInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HostRequestSearchServiceImpl implements HostRequestSearchService {
    @Autowired
    private HostRequestDAO hostRequestDAO;

    @Override
    public List<RequestListInfoVO> searchRequestList(int hostId) {
        return hostRequestDAO.requestList(hostId);
    }
}
