package com.moa.model.service;

import java.util.Map;

public interface MemberRegistService {
    boolean addMember(Map<String, Object> userInfo, Map<String, Object> duplicationInfo);
}
