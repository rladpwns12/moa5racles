package com.moa.mybatis;

import com.moa.model.vo.LoginVO;
import com.moa.model.vo.SimpleUserInfoVO;
import com.moa.model.vo.Token;

import java.util.Map;


public interface TokenMapper {
    int insertToken(Token token);
    int updateToken(Token token);
    Token searchOneToken(String series);
    int deleteToken(String userName);
}
