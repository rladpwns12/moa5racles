package com.moa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "pay")
public class PayController {
    @RequestMapping(value = "/card", method = RequestMethod.GET)
    public ModelAndView card() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("card");
//        mav.addObject("name", "최모아");
//        mav.addObject("email", "mmmm@naver.com");
//        mav.addObject("phone", "010-3904-9047");
//        mav.addObject("address", "신선로");
        mav.addObject("totalPrice", 100);
        return mav;
    }

    @RequestMapping(value = "/kakao", method = RequestMethod.GET)
    public ModelAndView kakao() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("kakao");
//        mav.addObject("name", "레이첼");
//        mav.addObject("email", "rCh@naver.com");
//        mav.addObject("phone", "010-3904-9047");
//        mav.addObject("address", "L.A");
//        mav.addObject("totalPrice", 100);
        return mav;
    }
}
