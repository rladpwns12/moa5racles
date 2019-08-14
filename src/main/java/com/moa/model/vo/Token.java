package com.moa.model.vo;
import lombok.Data;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import java.util.Date;

@Data
public class Token {

    private String username;
    private String series;
    private String token;
    private Date date;

    public Token() {}

    public Token(PersistentRememberMeToken persistentRememberMeToken) {
        this.username = persistentRememberMeToken.getUsername();
        this.series = persistentRememberMeToken.getSeries();
        this.date = persistentRememberMeToken.getDate();
        this.token = persistentRememberMeToken.getTokenValue();
    }

    public Token(String series, String token, Date lastUsed) {
        this.series = series;
        this.token = token;
        this.date = lastUsed;
    }
}