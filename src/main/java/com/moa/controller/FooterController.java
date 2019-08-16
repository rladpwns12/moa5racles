package com.moa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FooterController {

    @RequestMapping(value="/privacyPolicy", method = RequestMethod.GET)
    public String privacyPolicy() {
        return "privacyPolicy";
    }

    @RequestMapping(value="/termsOfUsePolicy", method = RequestMethod.GET)
    public String termsOfUsePolicy() {
        return "termsOfUsePolicy";
    }
}
