package com.moa.model.service;

import com.moa.model.dao.AdminHostDAO;
import com.moa.model.vo.AdminHostSimpleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HostConfirmServiceImpl implements HostConfirmService {
    @Autowired
    private AdminHostDAO adminHostDAO;

    public List<AdminHostSimpleVO> searchHostConfirmList(){
        return adminHostDAO.searchAdminHost();
    }

    public Map<String, Object> searchRequestInfo(int userId, String storageType){
        return adminHostDAO.searchAdminHostDetail(userId, storageType);
    }

    public boolean processConfirm(int userId, String context){
        Map<String, Object> info = new HashMap<>();
        info.put("userId", userId);
        info.put("context", context);
        return adminHostDAO.processConfirm(info);
    }

    public boolean processRefuse(int userId, String context){
        Map<String, Object> info = new HashMap<>();
        info.put("userId", userId);
        info.put("context", context);
        return adminHostDAO.processRefuse(info);
    }
}
