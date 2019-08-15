package com.moa.model.service;

import com.moa.model.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public class FindUserInfoServiceImpl implements FindUserInfoService{
    @Autowired
    private UserDAO userDAO;

    @Override
    public String findEmail(Map<String, Object> findEmailInfo) {
        return userDAO.findEmail(findEmailInfo);
    }

    @Override
    public boolean findPassword(Map<String, Object> findPasswordInfo) {
        return userDAO.findPassword(findPasswordInfo);
    }
}
