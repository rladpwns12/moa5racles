package com.moa.phone;

import java.util.HashMap;
import org.json.simple.JSONObject;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

public class SendMessage {
    private static final String MOA_PHONENUMBER = "01087571048";

    public static void main(String[] args) {
        String api_key = "NCSE2E9JU5RLJYA5";
        String api_secret = "IG92AHEYCNYPGOEXJUP754YPJJLBG9QW";
        Message coolsms = new Message(api_key, api_secret);

        // 4 params(to, from, type, text) are mandatory. must be filled
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", "01068066766");
        params.put("from", MOA_PHONENUMBER);
        params.put("type", "SMS");
        params.put("text", "MOA 인증번호[1852]");
        params.put("app_version", "test app 1.2"); // application name and version

        try {
            JSONObject obj = (JSONObject) coolsms.send(params);
            System.out.println(obj.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }
    }
}
