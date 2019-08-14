package com.moa.security;
import com.moa.model.dao.TokenDAO;
import com.moa.model.vo.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class CustomTokenRepository implements PersistentTokenRepository {
    @Autowired
    private TokenDAO tokenDAO;

    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        tokenDAO.createNewToken(new Token(token));
    }

    @Override
    public void updateToken(String series, String token, Date lastUsed) {
        tokenDAO.updateToken(new Token(series, token, lastUsed));
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        Token token = tokenDAO.getTokenForSeries(seriesId);
        return new PersistentRememberMeToken(token.getUsername(),
                token.getSeries(), token.getToken(), token.getDate());
    }

    @Override
    public void removeUserTokens(String username) {
        tokenDAO.removeUserTokens(username);
    }
}
