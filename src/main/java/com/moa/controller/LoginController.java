package com.moa.controller;

import com.moa.model.service.FindUserInfoService;
import com.moa.model.service.MemberInfoService;
import com.moa.model.service.MemberRegistService;
import com.moa.model.service.UserUpdateService;
import com.moa.model.vo.AddressVO;
import com.moa.model.vo.UserVO;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@Log4j
public class LoginController {
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    @Qualifier("memberService")
    @Autowired
    private MemberInfoService memberInfoService;
    @Autowired
    private MemberRegistService memberRegistService;
    @Autowired
    private FindUserInfoService findUserInfoService;
    @Autowired
    private UserUpdateService userUpdateService;


    @RequestMapping(value="/admin/login")
    public String adminLogin(String error, String logout, Model model){
        log.info("adminLoginPage()...");
        return "mLogin";
    }

    @RequestMapping(value="/userLogin")
    public String loginPage(String error, String logout, Model model){
        log.info("loginPage()...");
        return "userLogin";
    }
    @RequestMapping(value="/exit")
    public String exitRedirect(String error, String logout, Model model){
        log.info("exitRedirect()...");
        return "exitRedirect";
    }

    @RequestMapping(value = "/checkEmail", method = RequestMethod.POST)
    @ResponseBody
    public boolean checkEmail(@RequestParam(value = "email") String email){
        Map<String, Object> duplicationInfo = new HashMap<>();
        duplicationInfo.put("email", email);

        /*//이메일 중복이 있는 경우 true 반환
        if (memberInfoService.signUpDuplicationCheck(duplicationInfo))
            return FAIL;
        else
            return SUCCESS;*/
        return !memberInfoService.signUpDuplicationCheck(duplicationInfo);
    }

    @RequestMapping(value = "/checkNick", method = RequestMethod.POST)
    @ResponseBody
    public boolean checkNick(@RequestParam(value = "nick") String nick) {
        Map<String, Object> duplicationInfo = new HashMap<>();
        duplicationInfo.put("nick", nick);

        /*//닉네임 중복이 있는 경우 true 반환
        if (memberInfoService.signUpDuplicationCheck(duplicationInfo))
            return FAIL;
        else
            return SUCCESS;*/
        return !(memberInfoService.signUpDuplicationCheck(duplicationInfo));
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

    // 아이디, 비밀번호 찾기
    @RequestMapping(value = "/searchId", method = RequestMethod.GET)
    public String searchId() {
        return "searchId";
    }

    @RequestMapping(value = "/searchId", method = RequestMethod.POST)
    public @ResponseBody String isIdSearched(
            @RequestParam String name,
            @RequestParam String phone) {
        Map<String, Object> findEmailInfo = new HashMap<String, Object>();

        findEmailInfo.put("name", name);
        findEmailInfo.put("phoneNumber", phone);

        return findUserInfoService.findEmail(findEmailInfo);
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
        Map<String, Object> findPasswordInfo = new HashMap<String, Object>();

        findPasswordInfo.put("name", name);
        findPasswordInfo.put("phoneNumber", phone);
        findPasswordInfo.put("email", email);

        return findUserInfoService.findPassword(findPasswordInfo);
    }

    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public boolean updatePassword(
            @RequestParam String email,
            @RequestParam String name,
            @RequestParam String password) {
        Map<String, Object> newPasswordInformation = new HashMap<>();

        newPasswordInformation.put("email", email);
        newPasswordInformation.put("name", name);
        newPasswordInformation.put("password", password);

        return userUpdateService.updateUserPasswordByEmailAndName(newPasswordInformation);
    }

    @RequestMapping(value = "/termsOfService", method = RequestMethod.GET)
    public String termsOfService() {
        return "termsOfService";
    }

}
