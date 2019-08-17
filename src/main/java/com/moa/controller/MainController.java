package com.moa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @RequestMapping(value = {"main",""})
    public String main(){

        return "main";
    }

    @RequestMapping(value = "admin/login")
    public String mobileMain(){

        return "mLogin";
    }

    @RequestMapping(value = "admin/hostapprove/list")
    public String mobileHostApprove(){

        return "mHostApprove";
    }
    @RequestMapping(value = "admin/hostapprove/info")
    public String mobileApproveInformation(){

        return "mApproveInformation";
    }
}
