package com.moa.mobile.controller;

import com.moa.mobile.model.service.UserApplySearchService;
import com.moa.mobile.model.vo.ApplyListInfoVO;
import com.moa.model.service.LuggageRequestInfoService;
import com.moa.model.vo.ReadStoreRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/mobile/user")
public class UserController {
    @Autowired
    private UserApplySearchService userApplySearchService;
    @Autowired
    private LuggageRequestInfoService luggageRequestInfoService;

    @RequestMapping(value = "/list")
    public List<ApplyListInfoVO> requestList(){
        //임시 아이디
        int userId = 1;
        List<ApplyListInfoVO> list = userApplySearchService.searchApplyList(userId);
        return list;
    }
    @RequestMapping(value="/info/{requestNum}", method = RequestMethod.GET)
    @ResponseBody
    public ReadStoreRequestVO myPageRequestInfo(@PathVariable("requestNum") int requestId){
        ReadStoreRequestVO requestVO = luggageRequestInfoService.selectLuggageRequestInfo(requestId);
        requestVO.setApplicationDate(requestVO.getApplicationDate());
        return requestVO;
    }
}
