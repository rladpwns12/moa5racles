package com.moa.model.dao;


import com.moa.model.vo.LoginVO;
import com.moa.model.vo.SimpleUserInfoVO;
import com.moa.mybatis.UserMapper;
import lombok.NoArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@NoArgsConstructor
public class UserDAOImpl implements UserDAO {
    @Autowired
    private SqlSession sqlSession;

    @Override
    public SimpleUserInfoVO selectUserInfo(int userId) {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        return mapper.selectUserInfo(userId);
    }

    @Override
    public boolean checkExistUser(String userNick) {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int result = mapper.selectUserExist(userNick);
        if(result == 1)
            return true;
        return false;
    }

    @Override
    public boolean signUpUser(Map<String, Object> userInfo) {
        UserMapper mapper;
        boolean result;

        mapper = sqlSession.getMapper(UserMapper.class);
        mapper.signUpUser(userInfo);
        result = ((int)userInfo.get("res") == 1) ? true : false;

        return result;
    }

    @Override
    public boolean signUpDuplicationCheck(Map<String, Object> duplicationInfo) {
        UserMapper mapper;
        boolean result;

        mapper = sqlSession.getMapper(UserMapper.class);
        result = (mapper.duplicationCheck(duplicationInfo) == 1) ? true : false;

        return result;
    }

    @Override
    public LoginVO checkLogin(Map<String, Object> loginInfo) {
        UserMapper mapper;
        LoginVO result;

        mapper = sqlSession.getMapper(UserMapper.class);
        result = mapper.checkLogin(loginInfo);

        return result;
    }
}
