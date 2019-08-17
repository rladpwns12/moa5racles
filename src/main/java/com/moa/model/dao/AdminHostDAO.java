package com.moa.model.dao;

import com.moa.model.vo.AdminHostSimpleVO;


import java.util.List;
import java.util.Map;

public interface AdminHostDAO {
    List<AdminHostSimpleVO> searchAdminHost();
    Map<String,Object> searchAdminHostDetail(int userId, String storageType);
    boolean processConfirm(Map<String, Object> info);
    boolean processRefuse(Map<String, Object> info);
}