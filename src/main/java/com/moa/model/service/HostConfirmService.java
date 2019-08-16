package com.moa.model.service;

import com.moa.model.vo.AdminHostSimpleVO;
import com.moa.model.vo.HostRequestInfoVO;

import java.util.List;

public interface HostConfirmService {
    List<AdminHostSimpleVO> searchHostConfirmList();
    HostRequestInfoVO searchRequestInfo(String email);
}
