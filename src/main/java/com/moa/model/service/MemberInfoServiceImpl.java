package com.moa.model.service;


import com.moa.model.dao.UserDAO;
import com.moa.model.vo.LoginVO;
import com.moa.model.vo.SimpleUserInfoVO;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@NoArgsConstructor
public class MemberInfoServiceImpl implements  MemberInfoService {
    @Autowired
    private UserDAO userDAO;

    public boolean checkExistUser(String userNick){
        return userDAO.checkExistUser(userNick);
    }
    public SimpleUserInfoVO selectMemberInfo(int userId){
        return userDAO.selectUserInfo(userId);
    }
    public boolean signUpMember(Map<String, Object> userInfo){return userDAO.signUpUser(userInfo); }
    public boolean signUpDuplicationCheck(Map<String, Object> duplicationInfo){return userDAO.signUpDuplicationCheck(duplicationInfo); }
    public LoginVO memberLogin(Map<String, Object> loginInfo){return userDAO.checkLogin(loginInfo); }
}
