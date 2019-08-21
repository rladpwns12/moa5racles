package com.moa.controller;

import com.moa.model.service.PayService;
import com.moa.model.vo.PayVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PayController {
    @Autowired
    private PayService payService;

    @RequestMapping(value = "/pay")
    public @ResponseBody
    boolean pay(@RequestBody PayVO payVO) {
        return payService.updateHistory(payVO);
    }


//    @RequestMapping(value = "/card", method = RequestMethod.GET)
//    public @ResponseBody
//    boolean card(@RequestBody PayVO payVO) {
//        return payService.updateHistory(payVO);
//    }
//
//    @RequestMapping(value = "/kakao", method = RequestMethod.POST)
//    public @ResponseBody
//    boolean kakao(@RequestBody PayVO payVO) {
//        return payService.updateHistory(payVO);
//    }
}
