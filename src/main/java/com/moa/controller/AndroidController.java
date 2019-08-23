package com.moa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AndroidController {
    @RequestMapping("/userAndroid")
    public String userLogin(){
        return "callActivity";
    }
}
