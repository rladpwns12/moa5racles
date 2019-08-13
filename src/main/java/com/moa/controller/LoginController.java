package com.moa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    // 로그인
    @RequestMapping(value = "")
    public String loginPage() {
//        request.getSession();
        return "login";
    }

    // ??
    @RequestMapping(value = "/do")
    @ResponseBody
    public String login(@RequestParam(value = "email") String email,
                        @RequestParam(value = "password") String password) {
        return "success";
    }

    // 회원가입
    @RequestMapping("/registration")
    public String registeration(HttpServletRequest request)
    {
        return "registration";
    }

    @RequestMapping("/registerationForm")
    @ResponseBody
    public String registerationForm(
            @RequestParam String name,
            @RequestParam String nickname)
    {
        return "1";
    }

    // 아이디, 비밀번호 찾기
    @RequestMapping("/searchId")
    public String searchId() {
        return "searchId";
    }

    @RequestMapping("/searchPassword")
    public String searchPassword() {
        return "searchPassword";
    }
}
