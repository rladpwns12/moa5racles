package com.moa.controller;

import com.moa.phone.PhoneMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class TextMessageController {

    @RequestMapping(value = "authenticatePhone")
    public String authenticatePhone(){

        return "authenticatePhonePopup";
    }

    @RequestMapping(value = "send/textmessage", method = RequestMethod.POST)
    public @ResponseBody
    boolean sendPhoneMessage(@RequestBody Map<String,Object> data) {
        //-- start of validation
        if (data.get("phoneNumber").toString().equals("") || data.get("phoneNumber") == null
                || data.get("randomNumber").toString().equals("") || data.get("randomNumber") == null) {
            return false;
        }
        //-- end of validation

        //-- start of send message
        PhoneMessage phoneMessage = new PhoneMessage();
        boolean result = false;
        result = phoneMessage.sendAuthenticationMessage(data.get("phoneNumber").toString(), data.get("randomNumber").toString());


        return result;
    }
}
