package com.moa.controller;


import com.moa.message.MessengerStateMessage;
import com.moa.message.ValidMessage;
import com.moa.model.service.FindReportService;
import com.moa.model.service.MakeReportService;
import com.moa.model.vo.CustomUser;
import com.moa.model.vo.LoginVO;
import com.moa.model.vo.ReportVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping(value = "/report")
public class ReportContoller implements ValidMessage {
    @Autowired
    private MakeReportService makeReportService;
    @Autowired
    private FindReportService findReportService;

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public String reportPopup(){

        return "reportSend";
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    @ResponseBody
    public String sendReport(@RequestBody @Valid ReportVO reportVO, BindingResult bindingResult, Authentication auth){
        CustomUser customUser=(CustomUser)auth.getPrincipal();
        LoginVO loginVO=customUser.getLoginVO();
        if(reportVO.getUserId()!=Long.parseLong(loginVO.getUserId()))
            return MISMATCH;
        if(reportVO.getTargetUserNick().equals(loginVO.getNick()))
            return SELF;
        if(bindingResult.hasErrors()){
            if(reportVO.getTargetUserNick()==null || reportVO.getTargetUserNick().trim().equals(""))
                return BLANK;
            if(bindingResult.hasFieldErrors("targetUserNick"))
                return (TARGET+TOOLONG);
            else
                return (MESSAGE_CONTENT+TOOLONG);
        }
        if(makeReportService.makeReport(reportVO))
            return OK;
        else
            return FAIL;
    }

    @RequestMapping(value = {"/list/{pageNum}","/list"})
    @ResponseBody
    public ModelAndView selectReportList(@PathVariable Optional<Integer> pageNum,
                                         Authentication auth){
        ModelAndView mav = new ModelAndView();
        Map<String, Object> map = new HashMap<>();
        int curPage = 1;
        if(pageNum.isPresent()){
            curPage = pageNum.get();
        }

        CustomUser customUser = (CustomUser) auth.getPrincipal();
        int userId = Integer.parseInt(customUser.getLoginVO().getUserId());

        map.put("userId", userId);
        map.put("curPage", curPage);

        mav.addObject("curPage", curPage);
        mav.addObject("info", findReportService.findReportList(map));
        mav.setViewName("reportList");

        return mav;
    }

    @RequestMapping("/detail/{reportId}")
    public ModelAndView reportDetail(@PathVariable("reportId") long reportId){
        ModelAndView mav = new ModelAndView();
        Map<String, Object> info = findReportService.findReport(reportId);

        mav.addObject("info", info);
        mav.setViewName("reportDetail");

        return mav;
    }

}
