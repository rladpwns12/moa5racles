package com.moa.controller;


import com.moa.phone.PhoneMessage;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    @RequestMapping(value = {"/main", ""})
    public String main() {
        return "main";
    }

    @RequestMapping(value = {"admin/hostapprove/list","admin"})
    public String mobileHostApprove(){

        return "mHostApprove";
    }

    @RequestMapping(value = "admin/hostapprove/info")
    public String mobileApproveInformation(){

        return "mApproveInformation";

    }
}
