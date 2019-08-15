package com.moa.model.service;

import com.moa.model.dao.UserDAO;
import com.moa.model.vo.AddressVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserUpdateServiceImpl implements UserUpdateService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public boolean updateUserInformation(Map<String, Object> newUserInfo) {
        boolean result = false;
        if(userDAO.updateUser(newUserInfo)>=1){
            result = true;
        }
        return result;
    }

    @Override
    public boolean updateUserPassword(Map<String, Object> newPasswordInfo) {
        boolean result = false;
        if(userDAO.updatePassword(newPasswordInfo) >= 1){
            result = true;
        }
        return result;
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
