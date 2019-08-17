package com.moa.mybatis;

import com.moa.model.vo.AdminHostSimpleVO;

import java.util.List;
import java.util.Map;

public interface AdminHostMapper {
    List<AdminHostSimpleVO> selectHostConfirmList();
    Map<String,Object> selectConfirmDetail(Map<String,Object> info);
    int processConfirm(Map<String, Object> info);
    int processRefuse(Map<String, Object> info);
}