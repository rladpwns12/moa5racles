package com.moa.controller;


import com.moa.model.service.FindReportService;
import com.moa.model.service.MakeReportService;
import com.moa.model.vo.CustomUser;
import com.moa.model.vo.ReportVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
@RequestMapping(value = "/report")
public class ReportContoller {
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
    public boolean sendReport(@RequestBody ReportVO reportVO){
        return makeReportService.makeReport(reportVO);
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
