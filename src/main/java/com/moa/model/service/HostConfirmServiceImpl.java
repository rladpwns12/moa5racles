package com.moa.model.service;

import com.moa.model.dao.AdminHostDAO;
import com.moa.model.vo.AdminHostSimpleVO;
import com.moa.model.vo.HostRequestInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HostConfirmServiceImpl implements HostConfirmService {
    @Autowired
    private AdminHostDAO adminHostDAO;

    public List<AdminHostSimpleVO> searchHostConfirmList(){
        return adminHostDAO.searchAdminHost();
    }

    public HostRequestInfoVO searchRequestInfo(String email){
        return new HostRequestInfoVO();
    }
}
