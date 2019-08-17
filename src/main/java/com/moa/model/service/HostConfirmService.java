package com.moa.model.service;

import com.moa.model.vo.AdminHostSimpleVO;

import java.util.List;
import java.util.Map;

public interface HostConfirmService {
    List<AdminHostSimpleVO> searchHostConfirmList();
    Map<String,Object> searchRequestInfo(int userId, String storageType);
    boolean processConfirm(int userId, String context);
    boolean processRefuse(int userId, String context);
}
