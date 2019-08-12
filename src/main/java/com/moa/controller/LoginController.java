package com.moa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/login")
public class LoginController {

    @RequestMapping(value="")
    public String loginPage(){
        return "login";
    }

    @RequestMapping(value="/do")
    @ResponseBody
    public String login(@RequestParam(value = "email") String email,
                        @RequestParam(value = "password") String password){



        return "success";
    }
}
