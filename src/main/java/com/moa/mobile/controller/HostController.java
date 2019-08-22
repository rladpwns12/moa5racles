package com.moa.mobile.controller;

import com.moa.mobile.model.service.HostRequestSearchService;
import com.moa.mobile.model.vo.RequestListInfo;
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
    public List<RequestListInfo> requestList(){
        //임시 아이디
        int userId = 7;
        return hostRequestSearchService.searchRequestList(userId);
    }
}
