package com.moa.mobile.controller;

import com.moa.mobile.model.service.UserApplySearchService;
import com.moa.mobile.model.vo.ApplyListInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/mobile/user")
public class UserController {
    @Autowired
    private UserApplySearchService userApplySearchService;


    @RequestMapping(value = "/list")
    public List<ApplyListInfoVO> requestList(){
        //임시 아이디
        int userId = 1;
        List<ApplyListInfoVO> list = userApplySearchService.searchApplyList(userId);
        System.out.println(list);
        return list;
    }
}
