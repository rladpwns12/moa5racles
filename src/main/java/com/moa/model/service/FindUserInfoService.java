package com.moa.model.service;

import java.util.Map;

public interface FindUserInfoService {
    String findEmail(Map<String,Object> findEmailInfo);
    boolean findPassword(Map<String, Object> findPasswordInfo);
}
