package com.moa.mybatis.mobile;


public interface PushTokenMapper {
    String[] selectPushToken();
    int insertPushToken(String token);
}
