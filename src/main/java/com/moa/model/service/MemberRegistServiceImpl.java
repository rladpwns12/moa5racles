package com.moa.model.service;

import com.moa.model.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MemberRegistServiceImpl implements MemberRegistService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public boolean addMember(Map<String, Object> userInfo, Map<String, Object> duplicationInfo) {
        if(userInfo == null ||duplicationInfo == null) {
            return false;
        }
        if(userDAO.signUpDuplicationCheck(duplicationInfo)) {
           return false;
        }
        return userDAO.signUpUser(userInfo);
    }
}
