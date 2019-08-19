package com.moa.controller;


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
    public boolean sendReport(){
//        System.out.println(map.get("userId"));
//        System.out.println(map.get("content"));
//        System.out.println(map.get("targetUserNick"));
//        System.out.println(map.get("targetId"));
//        System.out.println(map.get("targetType"));
//        Iterator<String> keys = map.keySet().iterator();
//        while(keys.hasNext()){
//            System.out.println("값 : " + keys.next());
//        }
        return true;
    }
//    userId: userId,
//    content: content,
//    targetUserNick: targetUserNick,
//    targetId: targetId,
//    targetType: targetType
}
