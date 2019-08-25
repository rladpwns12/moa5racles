package com.moa.model.dao;

import com.moa.mybatis.mobile.PushTokenMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PushTokenDAOImpl implements PushTokenDAO {
    @Autowired
    SqlSession sqlSession;

    @Override
    public String[] searchTokenList() {
        PushTokenMapper mapper = sqlSession.getMapper(PushTokenMapper.class);
        return mapper.selectPushToken();
    }

    @Override
    public boolean createPushToken(String token) {
        PushTokenMapper mapper = sqlSession.getMapper(PushTokenMapper.class);

        return mapper.insertPushToken(token) >= 1 ? true : false;
    }
}
