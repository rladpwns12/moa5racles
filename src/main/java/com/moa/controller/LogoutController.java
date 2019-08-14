package com.moa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/userlogout")
public class LogoutController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String logout(){
        System.out.println("로그아웃 성공");
        return "main";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public void logoutpost(){
        System.out.println("logout 성공");
    }


}
