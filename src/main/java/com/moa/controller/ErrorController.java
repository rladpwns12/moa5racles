package com.moa.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController {
    private static final String ROLE_USER = "ROLE_USER";
    private static final String ROLE_PRE_HOST = "ROLE_PRE_HOST";
    private static final String ROLE_HOST = "ROLE_HOST";
    private static final Integer ADMIN_PAGE_CODE = 1;
    private static final Integer HOST_PAGE_CODE = 2;
    private static final Integer PRE_HOST_PAGE_CODE = 3;

    @RequestMapping(value = "/accessdenied")
    public ModelAndView accessDenied(HttpServletRequest request, Authentication auth, Model model){
        ModelAndView mav = new ModelAndView();

        if(request.isUserInRole(ROLE_HOST)) {
            mav.addObject("errorCode", ADMIN_PAGE_CODE);
        }else if(request.isUserInRole(ROLE_PRE_HOST)){
            mav.addObject("errorCode", HOST_PAGE_CODE);
        }else if(request.isUserInRole(ROLE_USER)){
            mav.addObject("errorCode", PRE_HOST_PAGE_CODE);
        }
        mav.setViewName("accessDenied");

        return mav;
    }
}
