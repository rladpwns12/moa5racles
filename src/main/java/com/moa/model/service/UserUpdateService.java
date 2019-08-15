package com.moa.model.service;

import com.moa.model.vo.AddressVO;

import java.util.Map;

public interface UserUpdateService {
    boolean updateUserInformation(Map<String,Object> newUserInfo);
    boolean updateUserPassword(Map<String,Object> newPasswordInfo);
    boolean withdrawalUser(int userId);
}
