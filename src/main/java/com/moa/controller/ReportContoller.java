package com.moa.controller;


import com.moa.model.vo.ReportVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.Map;

@Controller
@RequestMapping(value = "/report")
public class ReportContoller {

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public String reportPopup(){

        return "reportSend";
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    @ResponseBody
    public boolean sendReport(@RequestBody ReportVO reportVO){
        System.out.println("reportVO : " + reportVO);

        return true;
    }
}
