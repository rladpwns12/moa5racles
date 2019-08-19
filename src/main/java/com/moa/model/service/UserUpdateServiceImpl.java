package com.moa.model.service;

import com.moa.model.dao.UserDAO;
import com.moa.model.vo.CustomUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserUpdateServiceImpl implements UserUpdateService {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public boolean updateUserInformation(Map<String, Object> newUserInfo,CustomUser customUser) {
        boolean result = false;
        customUser.getLoginVO().setPhoneNumber(newUserInfo.get("phoneNumber").toString());
        if(userDAO.updateUser(newUserInfo)==1){
            result = true;
        }
        return result;
    }

    @Override
    public boolean updateUserPassword(Map<String, Object> newPasswordInfo, CustomUser customUser) {
        boolean result = false;
        CustomUser cu = customUser;
        newPasswordInfo.put("password",passwordEncoder.encode(newPasswordInfo.get("password").toString()));

        if(userDAO.updatePassword(newPasswordInfo) >= 1){
            cu.getLoginVO().setPassword(newPasswordInfo.get("password").toString());
            result = true;
        }
        return result;
    }

    @Override
    public boolean updateUserPasswordByEmailAndName(Map<String, Object> newPasswordInfo) {
        newPasswordInfo.put("password",passwordEncoder.encode(newPasswordInfo.get("password").toString()));

        return userDAO.updatePasswordByEmailAndName(newPasswordInfo) >= 1 ? true : false;
    }

    @Override
    public boolean withdrawalUser(int userId) {
        boolean result = false;
        if(userDAO.withdrawalUser(userId) >= 1){
            result = true;
        }
        return result;
    }
}
