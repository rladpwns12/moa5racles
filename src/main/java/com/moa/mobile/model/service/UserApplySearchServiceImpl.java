package com.moa.mobile.model.service;

import com.moa.mobile.model.dao.UserApplyDAO;
import com.moa.mobile.model.vo.ApplyListInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserApplySearchServiceImpl implements UserApplySearchService{
    @Autowired
    private UserApplyDAO userApplyDAO;

    @Override
    public List<ApplyListInfoVO> searchApplyList(int userId) {
        return userApplyDAO.userApplyList(userId);
    }
}
