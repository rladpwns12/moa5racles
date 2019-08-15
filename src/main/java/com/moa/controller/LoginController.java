package com.moa.controller;

import com.moa.model.service.MemberInfoService;
import com.moa.model.service.MemberRegistService;
import com.moa.model.vo.AddressVO;
import com.moa.model.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    @Qualifier("memberService")
    @Autowired
    private MemberInfoService memberInfoService;
    @Autowired
    private MemberRegistService memberRegistService;

    @RequestMapping(value="/login")
    public String loginPage(String error, String logout, Model model){
        System.out.println("loginPage()...");
        return "login";
    }

    // 회원가입
    @RequestMapping(value="/registration", method=RequestMethod.GET)
    public String registeration() {
        return "registration";
    }

    @RequestMapping(value="/registration", method=RequestMethod.POST)
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
            @RequestParam double latitude,
            @RequestParam double longitude) {
        UserVO userVO = new UserVO(email, password, phone, nickname, name, null);
        AddressVO addressVO = new AddressVO(address, detailAddress, postcode, latitude, longitude);

        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("UserVO", userVO);
        userInfo.put("AddressVO", addressVO);
        userInfo.put("res", 0);

        Map<String, Object> duplicationInfo = new HashMap<>();
        duplicationInfo.put("email", email);
        duplicationInfo.put("nick", nickname);

        return memberRegistService.addMember(userInfo, duplicationInfo);
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
    boolean isPasswordSearched(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String phone) {
        System.out.println("name: " + name);
        System.out.println("email: " + email);
        System.out.println("phone: " + phone);

        return true;
    }

    @RequestMapping(value="/updatePassword", method = RequestMethod.POST)
    public @ResponseBody boolean updatePassword(@RequestParam String password) {
        System.out.println("password: " + password);
        return true;
    }
}
