package com.moa.model.dao;

import com.moa.model.vo.LoginVO;
import com.moa.model.vo.SimpleUserInfoVO;

import java.util.Map;

public interface UserDAO {
    SimpleUserInfoVO selectUserInfo(int userId);
    boolean checkExistUser(String userNick);
    boolean signUpUser(Map<String, Object> userInfo);
    boolean signUpDuplicationCheck(Map<String, Object> duplicationInfo);
    LoginVO checkLogin(Map<String, Object> loginInfo);
    String findEmail(Map<String,Object> findEmailInfo );
    int updatePassword(Map<String,Object> updatePasswordInfo);
    int updateUser(Map<String,Object> updateUserInfo);
}
