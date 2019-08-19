package com.moa.phone;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.HashMap;

public class PhoneMessage {
    private static final String API_KEY = "NCSAK6YSIR2SYIPG";
    private static final String API_SECRET = "AMEKQDW3CCBKWCPH9J802BVG3HZSA2VP";
    private static final String FROM_PHONENUMBER = "01087571048";
    private Message message;

    public PhoneMessage(){
        message = new Message(API_KEY, API_SECRET);
    }


    
    public boolean sendAuthenticationMessage(String phoneNumber, String randomNumber){
        // 4 params(to, from, type, text) are mandatory. must be filled
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", phoneNumber);
        params.put("from", FROM_PHONENUMBER);
        params.put("type", "SMS");
        params.put("text", "MOA 인증번호 [" +randomNumber+"]를 화면에 입력해주세요.");
        params.put("app_version", "test app 1.2"); // application name and version
        JSONObject obj = new JSONObject();
        try {
            obj = (JSONObject)message.send(params);
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }
        System.out.println(obj);
        //-- end of send message
        int count = 0;
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(obj != null) break;
            if(count++ >= 10){
                break;
            }
            System.out.println(obj+"is null");
                 }
        try{
            if(Integer.parseInt(obj.get("success_count").toString()) == 1 &&
                    Integer.parseInt(obj.get("success_count").toString()) != 0){
                return true;
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }


        return  false;
    }
}
