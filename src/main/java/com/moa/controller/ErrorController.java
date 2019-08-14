package com.moa.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
public class ErrorController {

    @RequestMapping(value = "/notHostErrorPage")
    public void accessDenied(Authentication auth, Model model){

        System.out.println(auth.toString());
        System.out.println("access denied");
        model.addAttribute("msg", "access Denied");
    }

}
