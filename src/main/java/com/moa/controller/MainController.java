package com.moa.controller;

import com.moa.phone.PhoneMessage;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@Controller
public class MainController {
    @RequestMapping(value = {"main",""})
    public String main(){

        return "main";
    }

    @RequestMapping(value = "test")
    public String test(){

        return "test";
    }
    @RequestMapping(value = "authenticatePhone")
    public String authenticatePhone(){

        return "authenticatePhonePopup";
    }

    @RequestMapping(value = "send/phonemessage", method = RequestMethod.POST)
    public @ResponseBody boolean sendPhoneMessage(@RequestBody Map<String,Object> data){
        //-- start of validation
        if(data.get("phoneNumber").toString().equals("") || data.get("phoneNumber") == null
        || data.get("randomNumber").toString().equals("") || data.get("randomNumber") == null){
            return false;
        }
        //-- end of validation

        //-- start of send message
        PhoneMessage phoneMessage = new PhoneMessage();
        boolean result = false;
        result = phoneMessage.sendAuthenticationMessage(data.get("phoneNumber").toString(),data.get("randomNumber").toString());


        return result;
    }
}
