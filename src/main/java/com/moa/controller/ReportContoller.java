package com.moa.controller;


import com.moa.model.service.MakeReportService;
import com.moa.model.vo.ReportVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/report")
public class ReportContoller {
    @Autowired
    private MakeReportService makeReportService;

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public String reportPopup(){

        return "reportSend";
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    @ResponseBody
    public boolean sendReport(@RequestBody ReportVO reportVO){
        return makeReportService.makeReport(reportVO);
    }
}
