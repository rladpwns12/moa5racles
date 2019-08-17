package com.moa.controller;

import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    @RequestMapping(value = {"/main", ""})
    public String main(HttpServletRequest request) {
        Device device = DeviceUtils.getCurrentDevice(request);

        if (device.isNormal()) {
            return "main";
        } else if (device.isTablet() || device.isMobile()) {
            return "admin/login";
        }
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
