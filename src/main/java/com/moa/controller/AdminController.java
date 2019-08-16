package com.moa.controller;

import com.moa.model.service.HostConfirmService;
import com.moa.model.vo.HostRequestInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    private HostConfirmService hostConfirmService;

    @RequestMapping(value = "/confirmlist", method = RequestMethod.GET)
    public ModelAndView confirmList(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("confirmWaitingList", hostConfirmService.searchHostConfirmList());
        mav.setViewName("/mobile/confirmList");
        return mav;
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    @ResponseBody
    public HostRequestInfoVO confirmProc(@RequestParam (value="email") String email){
        HostRequestInfoVO hostRequestInfoVO = hostConfirmService.searchRequestInfo(email);
        return hostRequestInfoVO;
    }
}
