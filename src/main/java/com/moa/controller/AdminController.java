package com.moa.controller;

import com.moa.model.service.HostConfirmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    private HostConfirmService hostConfirmService;

    @RequestMapping(value = {"","/hostapprove/list"}, method = RequestMethod.GET)
    public ModelAndView confirmList() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("confirmWaitingList", hostConfirmService.searchHostConfirmList());
        mav.setViewName("/admin/mHostApprove");

        return mav;
    }
    @RequestMapping(value = {"/report/list"}, method = RequestMethod.GET)
    public ModelAndView confirmReport() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("confirmWaitingList", hostConfirmService.searchHostConfirmList());
        mav.setViewName("/admin/mReport");

        return mav;
    }
    @RequestMapping(value = {"/history/list"}, method = RequestMethod.GET)
    public ModelAndView confirmHistory() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("confirmWaitingList", hostConfirmService.searchHostConfirmList());
        mav.setViewName("/admin/mReportInformation");

        return mav;
    }

    @RequestMapping(value = "/hostapprove/info", method = RequestMethod.GET)
    public ModelAndView confirmProc(@RequestParam("userId") int userId,
                                    @RequestParam("storageType")String storageType) {

        ModelAndView mav = new ModelAndView();
        Map<String, Object> info = new HashMap<>();

        info = hostConfirmService.searchRequestInfo(userId, storageType);
        mav.addObject("requestInfo", info);
        mav.setViewName("/admin/mApproveInformation");
        System.out.println(mav);

        return mav;
    }
    @RequestMapping(value = "/report/info", method = RequestMethod.GET)
    public ModelAndView reportInfo(@RequestParam("userId") int userId,
                                    @RequestParam("storageType")String storageType) {
        ModelAndView mav = new ModelAndView();
        Map<String, Object> info = new HashMap<>();
        info = hostConfirmService.searchRequestInfo(userId, storageType);
        mav.addObject("requestInfo", info);
        mav.setViewName("/admin/mReportInformation");
        return mav;
    }



    @RequestMapping(value = "/hostapprove/confirm")
    @ResponseBody
    public boolean confirm(@RequestParam(value = "userId") int userId,
                           @RequestParam(value = "context") String context) {

        return hostConfirmService.processConfirm(userId, context);
    }

    @RequestMapping(value = "/hostapprove/refuse")
    @ResponseBody
    public boolean refuse(@RequestParam(value = "userId") int userId,
                          @RequestParam(value = "context") String context) {
        System.out.println(userId+", " + context);
        return hostConfirmService.processRefuse(userId, context);
    }

}
