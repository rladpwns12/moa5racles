package com.moa.model.dao;

public interface PushTokenDAO {
    String[] searchTokenList();
    boolean createPushToken(String token);
}
