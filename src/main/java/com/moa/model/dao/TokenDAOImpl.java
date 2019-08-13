package com.moa.model.dao;

import com.moa.model.vo.Token;
import com.moa.mybatis.TokenMapper;
import lombok.Data;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;

@Repository
public class TokenDAOImpl implements TokenDAO {
    @Autowired
    private SqlSession sqlSession;

    @Override
    public int createNewToken(Token token) {
        TokenMapper tokenMapper = sqlSession.getMapper(TokenMapper.class);
        return tokenMapper.insertToken(token);
    }

    @Override
    public int updateToken(Token token) {
        TokenMapper tokenMapper = sqlSession.getMapper(TokenMapper.class);
        return tokenMapper.updateToken(token);
    }

    @Override
    public Token getTokenForSeries(String seriesId) {
        TokenMapper tokenMapper = sqlSession.getMapper(TokenMapper.class);
        return tokenMapper.searchOneToken(seriesId);
    }

    @Override
    public int removeUserTokens(final String username) {
        TokenMapper tokenMapper = sqlSession.getMapper(TokenMapper.class);
        return tokenMapper.deleteToken(username);
    }
}

