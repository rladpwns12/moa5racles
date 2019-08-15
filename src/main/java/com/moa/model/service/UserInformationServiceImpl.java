package com.moa.model.service;

import com.moa.model.dao.UserDAO;
import com.moa.model.vo.AddressVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInformationServiceImpl implements UserInformationService {
    @Autowired
    private UserDAO userDAO;
    @Override
    public AddressVO findUserAddress(int userId) {
        AddressVO addressVO = userDAO.searchAddress(userId);
        return addressVO;
    }
}
