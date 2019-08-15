package com.moa.model.dao;

import com.moa.model.vo.AddressVO;
import com.moa.model.vo.LoginVO;
import com.moa.model.vo.SimpleUserInfoVO;

import java.util.Map;

public interface UserDAO {
    SimpleUserInfoVO selectUserInfo(int userId);
    boolean checkExistUser(String userNick);
    boolean signUpUser(Map<String, Object> userInfo);
    boolean signUpDuplicationCheck(Map<String, Object> duplicationInfo);
    String findEmail(Map<String,Object> findEmailInfo );
    boolean findPassword(Map<String, Object> findPasswordInfo);
    int updatePassword(Map<String,Object> updatePasswordInfo);
    int updateUser(Map<String,Object> updateUserInfo);
    LoginVO checkLogin(String email);
    AddressVO searchAddress(int userId);
    int withdrawalUser(int userId);

}
