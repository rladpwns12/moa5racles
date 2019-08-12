package com.moa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegistrationController {
    @RequestMapping(value = "/registration")
    public String registeration() {
        return "registration";
    }
}
