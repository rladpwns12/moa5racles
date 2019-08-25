package com.moa.model.service;

import com.moa.model.dao.PushTokenDAO;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AndroidPushPeriodicNotification {
    @Autowired
    PushTokenDAO pushTokenDAO;
    public static final String MESSAGE_CODE_GREET = "GREET";
    public static final String MESSAGE_CODE_NEW_REQUEST = "REQUEST";
    public static final String MESSAGE_CODE_REQUEST_CONFIRM = "REQUEST_CONFIRM";

    public String PeriodicNotificationJson(String messageType) throws JSONException {

        String tokenData[] = pushTokenDAO.searchTokenList();
        JSONObject body = new JSONObject();

        List<String> tokenlist = new ArrayList<String>();

        for(int i=0; i<tokenData.length; i++){
            tokenlist.add(tokenData[i]);
        }

        JSONArray array = new JSONArray();

        for(int i=0; i<tokenlist.size(); i++) {
            array.put(tokenlist.get(i));
        }

        body.put("registration_ids", array);

        JSONObject notification = new JSONObject();

        notification.put("title","MOA");
        notification.put("body", messageType);
        //key 값을 notification으로 하면 app이 메모리에서 내려오거나 백그라운드에서 구동 중 일 경우
        // onMessageReceived가 호출되지 않는다.
        body.put("data", notification);

        System.out.println(body.toString());

        return body.toString();
    }

    public String PeriodicNotificationJson(String messageType, String token) throws JSONException {

        String tokenData[] = {token};
        JSONObject body = new JSONObject();

        List<String> tokenlist = new ArrayList<String>();

        for(int i=0; i<tokenData.length; i++){
            tokenlist.add(tokenData[i]);
        }

        JSONArray array = new JSONArray();

        for(int i=0; i<tokenlist.size(); i++) {
            array.put(tokenlist.get(i));
        }

        body.put("registration_ids", array);

        JSONObject notification = new JSONObject();

        notification.put("title","MOA");
        notification.put("body", messageType);
        //key 값을 notification으로 하면 app이 메모리에서 내려오거나 백그라운드에서 구동 중 일 경우
        // onMessageReceived가 호출되지 않는다.
        body.put("data", notification);

        System.out.println(body.toString());

        return body.toString();
    }
}