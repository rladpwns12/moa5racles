package com.moa.mybatis;

import com.moa.model.vo.AddressVO;
import com.moa.model.vo.LoginVO;
import com.moa.model.vo.SimpleUserInfoVO;

import java.util.Map;


public interface UserMapper {
    SimpleUserInfoVO selectUserInfo(int userId);
    int selectUserExist(String userNick);
    void signUpUser(Map<String,Object> userInfo);
    int duplicationCheck(Map<String,Object> duplicationInfo);
    LoginVO checkLogin(String email);
    String findPassword(Map<String, Object> findPasswordInfo);
    String findEmail(Map<String,Object> findEmailInfo );
    int updatePassword(Map<String,Object> updatePasswordInfo);
    int updatePasswordByEmailAndName(Map<String, Object> updatePasswordInfo);
    void updateUser(Map<String,Object> updateUserInfo);
    AddressVO searchAddress(int userId);
    int withdrawalUser(int userId);
}
