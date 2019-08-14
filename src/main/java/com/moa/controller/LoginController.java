package com.moa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    // 로그인
    @RequestMapping(value = "/login")
    public String loginPage() {
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
    public String registeration() {
        return "registration";
    }

    @RequestMapping(value = "/registerationForm", method = RequestMethod.POST)
    @ResponseBody
    public boolean registerationForm(
            @RequestParam String name,
            @RequestParam String nickname,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String phone,
            @RequestParam String postcode,
            @RequestParam String address,
            @RequestParam String detailAddress,
            @RequestParam String latitude,
            @RequestParam String longitude) {
//        System.out.println(name);
//        System.out.println(nickname);
//        System.out.println(email);
//        System.out.println(password);
//        System.out.println(phone);
//        System.out.println(postcode);
//        System.out.println(address);
//        System.out.println(detailAddress);
//        System.out.println(latitude);
//        System.out.println(longitude);
        return true;
    }

    //회원가입 중복 검사
    @RequestMapping(value = "/checkNickname", method = RequestMethod.POST)
    public @ResponseBody
    boolean checkNickname(@RequestParam String nickname) {


        return true;
    }

    @RequestMapping(value = "/checkEmail", method = RequestMethod.POST)
    public @ResponseBody
    boolean checkEmail(@RequestParam String email) {


        return false;
    }

    // 아이디, 비밀번호 찾기
    @RequestMapping(value = "/searchId", method = RequestMethod.GET)
    public String searchId() {
        return "searchId";
    }

    @RequestMapping(value = "/searchId", method = RequestMethod.POST)
    public @ResponseBody String isIdSearched(
            @RequestParam String name,
            @RequestParam String phone) {
        System.out.println("name: " + name);
        System.out.println("phone: " + phone);
        return "success";
    }

    @RequestMapping(value = "/searchPassword", method = RequestMethod.GET)
    public String searchPassword() {
        return "searchPassword";
    }

    @RequestMapping(value = "/searchPassword", method = RequestMethod.POST)
    public @ResponseBody
    String isPasswordSearched(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String phone) {
        System.out.println("name: " + name);
        System.out.println("email: " + email);
        System.out.println("phone: " + phone);

        return "failed";
    }
}
