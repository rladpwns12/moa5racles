package com.moa.model.dao;
import com.moa.model.vo.Token;
import java.util.Date;


public interface TokenDAO {
    public int createNewToken(Token token) ;
    public int updateToken(Token token);
    public Token getTokenForSeries(String seriesId);
    public int removeUserTokens(final String username);
}

