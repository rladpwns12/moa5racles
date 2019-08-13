package com.moa.controller;

import com.moa.model.service.MemberInfoService;
import com.moa.model.service.MemberInfoServiceImpl;
import com.moa.model.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value="/login")
public class LoginController {
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    @Qualifier("memberService")
    @Autowired
    private MemberInfoService memberInfoService;

    @RequestMapping(value="")
    public String loginPage(String error, String logout, Model model){
        System.out.println(error);
        System.out.println(logout);
        return "login";
    }

    public String checkEmail(@RequestParam(value = "email") String email){
        Map<String, Object> duplicationInfo = new HashMap<>();

        duplicationInfo.put("email", email);
        if(memberInfoService.signUpDuplicationCheck(duplicationInfo))
            return SUCCESS;
        else
            return FAIL;
    }

    @RequestMapping(value = "/checkNick")
    @ResponseBody
    public String checkNick(@RequestParam(value = "nick") String nick) {
        Map<String, Object> duplicationInfo = new HashMap<>();

        duplicationInfo.put("nick", nick);
        if (memberInfoService.signUpDuplicationCheck(duplicationInfo))
            return SUCCESS;
        else
            return FAIL;
    }
    // 회원가입
    @RequestMapping("/registration")
    public String registeration()
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
