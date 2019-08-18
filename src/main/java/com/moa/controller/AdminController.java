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

    @RequestMapping(value = "/confirm")
    @ResponseBody
    public boolean confirm(@RequestParam(value = "userId") int userId,
                           @RequestParam(value = "context") String context) {

        return hostConfirmService.processConfirm(userId, context);
    }

    @RequestMapping(value = "/refuse")
    @ResponseBody
    public boolean refuse(@RequestParam(value = "userId") int userId,
                          @RequestParam(value = "context") String context) {
        System.out.println(userId+", " + context);
        return hostConfirmService.processRefuse(userId, context);
    }

}
