package com.moa.mybatis;

import com.moa.model.vo.SimpleUserInfoVO;

import java.util.Map;


public interface UserMapper {
    SimpleUserInfoVO selectUserInfo(int userId);
    int selectUserExist(String userNick);

    int signupUser(Map<String,Object> userInfo);
    int duplicationCheck(Map<String,Object> duplicationInfo);

//    signup Map 정보
//    - "userVO", userVO
//    - "addressVo", addressVO
//
//    duplicationCheck 정보
//    > 아래에서 검사할 것만 map에 담아서 보내주시면 됩니다.
//    - "userEmail", userEmail(String)
//    - "password", password(String)
//    - "userNick", userEmail(String)

}
