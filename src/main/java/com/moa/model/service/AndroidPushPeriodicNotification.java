package com.moa.model.service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AndroidPushPeriodicNotification {

    public static String PeriodicNotificationJson() throws JSONException {
        LocalDate localDate = LocalDate.now();

        String sampleData[] = {"dBDn6TXmZDI:APA91bH40GeqDMnypPFgqp7CRZ0DiuKBqhxIIO89uQiR2zCGSQQByagQTh8BGGtUTJHn0Zlsg35Exod-S2ROmmNJxzy6vzLNCOuwalHI2eiOSLWVYLpAFWU8Q53VDp1bVq7L_lWvPlOE"
                ,"f_uZr6HpcOY:APA91bHj1jDdTbUDutP55LBd8XTlE0R5ghRTSGGx10axUdkmKP5EeGOhfjdvmTXAKzYRnO3YcTfeSEAWUWClM21y9mMyVPc8cUIaqoV8vXQI12mPvOXV8Pw8qyNFuNQOMVCd35iVBt3U"};

        JSONObject body = new JSONObject();

        List<String> tokenlist = new ArrayList<String>();

        for(int i=0; i<sampleData.length; i++){
            tokenlist.add(sampleData[i]);
        }

        JSONArray array = new JSONArray();

        for(int i=0; i<tokenlist.size(); i++) {
            array.put(tokenlist.get(i));
        }

        body.put("registration_ids", array);

        JSONObject notification = new JSONObject();
        notification.put("title","hello!");
        notification.put("body","Today is "+localDate.getDayOfWeek().name()+"!");

        body.put("notification", notification);

        System.out.println(body.toString());

        return body.toString();
    }
}