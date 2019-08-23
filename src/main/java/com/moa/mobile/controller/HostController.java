package com.moa.mobile.controller;

import com.moa.mobile.model.service.HostRequestSearchService;
import com.moa.mobile.model.vo.RequestListInfoVO;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/mobile/host")
public class HostController {
    @Autowired
    private HostRequestSearchService hostRequestSearchService;


    @RequestMapping(value = "/list")
    public List<RequestListInfoVO> requestList(){
        //임시 아이디
        int userId = 7;
        List<RequestListInfoVO> list = hostRequestSearchService.searchRequestList(userId);
        System.out.println(list);
        return list;
    }
}
