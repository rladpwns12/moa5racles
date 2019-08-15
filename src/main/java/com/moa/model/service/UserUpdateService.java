package com.moa.model.service;

import com.moa.model.vo.AddressVO;
import com.moa.model.vo.CustomUser;

import java.util.Map;

public interface UserUpdateService {
    boolean updateUserInformation(Map<String,Object> newUserInfo, CustomUser customUser);
    boolean updateUserPassword(Map<String,Object> newPasswordInfo, CustomUser customUser);
    boolean withdrawalUser(int userId);
}
