package com.moa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @RequestMapping(value = {"main",""})
    public String main(){

        return "main";
    }

    @RequestMapping(value = "admin")
    public String mobileMain(){

        return "mMain";
    }
    @RequestMapping(value = "admin/hostapprove")
    public String mobileHostApprove(){

        return "mHostApprove";
    }
}
