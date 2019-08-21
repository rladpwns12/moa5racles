package com.moa.controller;

import com.moa.model.service.PayService;
import com.moa.model.vo.PayVO;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Log4j
@Controller
@RequestMapping(value = "/pay")
public class PayController {
    @Autowired
    private PayService payService;

    @RequestMapping(value = "/card", method = RequestMethod.GET)
    public boolean card(@RequestParam int price) {


        return false;
    }

    @RequestMapping(value = "/kakao", method = RequestMethod.POST)
    public @ResponseBody
    boolean kakao(@RequestBody PayVO payVO) {
        log.info("Controller");
        return payService.updateHistory(payVO);
    }
}
