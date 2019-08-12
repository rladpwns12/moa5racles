package com.moa.mybatis;

import com.moa.model.vo.SimpleUserInfoVO;

import java.util.Map;


public interface UserMapper {
    SimpleUserInfoVO selectUserInfo(int userId);
    int selectUserExist(String userNick);

    int checkLogin(Map<String,Object> loginInfo);
    int signupUser(Map<String,Object> userInfo);
    int duplicationCheck(Map<String,Object> duplicationInfo);
}
