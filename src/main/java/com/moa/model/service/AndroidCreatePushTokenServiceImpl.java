package com.moa.model.service;

import com.moa.model.dao.PushTokenDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AndroidCreatePushTokenServiceImpl implements AndroidCreatePushTokenService{
    @Autowired
    private PushTokenDAO pushTokenDAO;

    @Override
    public boolean insertPushToken(String token){
        return pushTokenDAO.createPushToken(token);
    }
}
