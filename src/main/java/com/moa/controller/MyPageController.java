package com.moa.controller;


import com.moa.message.MessengerStateMessage;
import com.moa.model.service.LuggageRequestInfoService;
import com.moa.model.service.LuggageRequestRecordService;
import com.moa.model.service.MemberInfoServiceImpl;
import com.moa.model.service.MessengerListServiceImpl;
import com.moa.model.vo.*;
import com.moa.paging.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping(value="/mypage")
public class MyPageController {
    @Autowired
    private LuggageRequestRecordService luggageRequestRecordService;
    @Autowired
    private LuggageRequestInfoService luggageRequestInfoService;
    @Autowired
    private MessengerListServiceImpl messengerListService;
    @Autowired

    @Qualifier("memberService")
    private MemberInfoServiceImpl memberInfoService;

    @RequestMapping(value="", method= RequestMethod.GET)
    public ModelAndView myPage(Authentication auth) {
        ModelAndView mav = new ModelAndView();
        int userId;

        CustomUser customUser = (CustomUser) auth.getPrincipal();
        userId = Integer.parseInt(customUser.getLoginVO().getUserId());

        SimpleUserInfoVO simpleUserInfoVO = memberInfoService.selectMemberInfo(userId);
        mav.addObject("profileName", simpleUserInfoVO.getProfileName());
        mav.addObject("userName", simpleUserInfoVO.getName());
        mav.addObject("userEmail", simpleUserInfoVO.getEmail());
        mav.addObject("requestCnt", simpleUserInfoVO.getRequestCnt());
        mav.addObject("notReadMessageCnt", simpleUserInfoVO.getNotReadMessageCnt());
        mav.addObject("usingStorageCnt", simpleUserInfoVO.getUsingStorageCnt());
        mav.setViewName("myPage");

        return mav;
    }

    @RequestMapping(value="/requestlist/{curPage}", method=RequestMethod.GET)
    public ModelAndView myPageRequestList(@PathVariable("curPage") int curPage, Authentication auth){
        ModelAndView mav = new ModelAndView();
        Map<String, Object> map = new HashMap<>();
        int userId;
        int totPageCnt;

        CustomUser customUser = (CustomUser) auth.getPrincipal();
        userId = Integer.parseInt(customUser.getLoginVO().getUserId());

        map = luggageRequestRecordService.selectLuggageRequestRecord(userId, curPage);
        totPageCnt = luggageRequestRecordService.LuggageRequestCountService(userId);

        mav.addObject("requestList", map.get("requestList"));
        mav.addObject("productList", map.get("productList"));
        mav.addObject("totPageCnt", totPageCnt);
        mav.addObject("curPage", curPage);
        mav.setViewName("requestList");

        return mav;
    }

    @RequestMapping(value="/requestlist/info/{requestNum}", method = RequestMethod.GET)
    @ResponseBody
    public ReadStoreRequestVO myPageRequestInfo(@PathVariable("requestNum") int requestId){
        ReadStoreRequestVO requestVO = luggageRequestInfoService.selectLuggageRequestInfo(requestId);

        requestVO.setApplicationDate(requestVO.getApplicationDate());
        return requestVO;
    }

    @RequestMapping(value = {"/message/receive","/message"})
    public ModelAndView message(Authentication auth){
        ModelAndView mav = new ModelAndView();
        int userId;

        CustomUser customUser = (CustomUser) auth.getPrincipal();
        userId = Integer.parseInt(customUser.getLoginVO().getUserId());

        Map<String,Object> messageInfo = new HashMap<String,Object>();
        messageInfo.put("messageType", MessengerStateMessage.MESSAGE_TYPE_RECEIVER);
        messageInfo.put("userId",userId);
        messageInfo.put("firstNum",1);
        messageInfo.put("lastNum",10);

        List<MessageVO> list = messengerListService.messageList(messageInfo);
        int allListCnt = messengerListService.messageListCnt(messageInfo);

        //--paging logic
        Pagination pagination = new Pagination(allListCnt,1,list.size());

        //ModelAndView
        mav.setViewName("messageReceive");
        mav.addObject(list);
        mav.addObject(pagination);

        return mav;
    }
    @RequestMapping("/message/receive/{curPage}")
    public ModelAndView messageCurPage(Authentication auth,
                                       @PathVariable int curPage){
        int userId;
        ModelAndView mav = new ModelAndView();

        CustomUser customUser = (CustomUser) auth.getPrincipal();
        userId = Integer.parseInt(customUser.getLoginVO().getUserId());

        //--list logic
        Map<String,Object> messageInfo = new HashMap<String,Object>();
        messageInfo.put("messageType",MessengerStateMessage.MESSAGE_TYPE_RECEIVER);
        messageInfo.put("userId",userId);
        messageInfo.put("firstNum",(curPage-1)*10+1);
        messageInfo.put("lastNum",(curPage-1)*10+10);

        List<MessageVO> list = messengerListService.messageList(messageInfo);
        int allListCnt = messengerListService.messageListCnt(messageInfo);

        //--paging logic
        Pagination pagination = new Pagination(allListCnt,curPage,list.size());

        //ModelAndView
        mav.setViewName("messageReceive");
        mav.addObject(list);
        mav.addObject(pagination);



        return mav;
    }

    //보낸 메세지

    @RequestMapping("/message/send")
    public ModelAndView messageSendCurPage(Authentication auth){
        int userId;
        ModelAndView mav = new ModelAndView();

        CustomUser customUser = (CustomUser) auth.getPrincipal();
        userId = Integer.parseInt(customUser.getLoginVO().getUserId());
        //--list logic
        Map<String,Object> messageInfo = new HashMap<String,Object>();
        messageInfo.put("messageType", MessengerStateMessage.MESSAGE_TYPE_SENDER);
        messageInfo.put("userId",userId);
        messageInfo.put("firstNum",1);
        messageInfo.put("lastNum",10);

        List<MessageVO> list = messengerListService.messageList(messageInfo);
        int allListCnt = messengerListService.messageListCnt(messageInfo);

        //--paging logic
        Pagination pagination = new Pagination(allListCnt,1,list.size());

        //ModelAndView
        mav.setViewName("messageSend");
        mav.addObject(list);
        mav.addObject(pagination);


        return mav;
    }
    @RequestMapping("/message/send/{curPage}")
    public ModelAndView messageSend(Authentication auth,
                                    @PathVariable int curPage){
        int userId;
        ModelAndView mav = new ModelAndView();

        CustomUser customUser = (CustomUser) auth.getPrincipal();
        userId = Integer.parseInt(customUser.getLoginVO().getUserId());

        //--list logic
        Map<String,Object> messageInfo = new HashMap<String,Object>();
        messageInfo.put("messageType", MessengerStateMessage.MESSAGE_TYPE_SENDER);
        messageInfo.put("userId",userId);
        messageInfo.put("firstNum",(curPage-1)*10+1);
        messageInfo.put("lastNum",(curPage-1)*10+10);

        List<MessageVO> list = messengerListService.messageList(messageInfo);
        int allListCnt = messengerListService.messageListCnt(messageInfo);

        //--paging logic
        Pagination pagination = new Pagination(allListCnt,curPage,list.size());

        //ModelAndView
        mav.setViewName("messageSend");
        mav.addObject(list);
        mav.addObject(pagination);



        return mav;
    }
    //메시지 상세보기
    @RequestMapping("/message/send/detail/{messageNum}")
    public ModelAndView messageSendDetail(@PathVariable int messageNum){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("messageDetail");
        MessageVO msg = messengerListService.messageDetail(messageNum);
        mav.addObject("messageInfo",msg);
        mav.addObject("messageType","send");
        return mav;
    }
    @RequestMapping("/message/receive/detail/{messageNum}")
    public ModelAndView messageReceiveDetail(@PathVariable int messageNum){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("messageDetail");
        MessageVO msg = messengerListService.messageDetail(messageNum);
        mav.addObject("messageInfo",msg);
        mav.addObject("messageType","receive");
        return mav;
    }
    @RequestMapping(value = {"/message/submit"}, method = RequestMethod.GET)
    public ModelAndView messageSubmitForm(Authentication auth){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("messageSendDetail");

        CustomUser customUser = (CustomUser) auth.getPrincipal();
        String nick = customUser.getLoginVO().getNick();

        mav.addObject("userNick",nick);
        return mav;
    }
    @ResponseBody
    @RequestMapping(value = {"/message/sendmessage"}, method = RequestMethod.POST)
    public boolean messageSubmit(@RequestBody Map<String,Object> messageSendInfo){
        if(memberInfoService.checkExistUser((String)messageSendInfo.get("receiverNick"))==false){
            return false;
        }
        return messengerListService.messageSend(messageSendInfo);
    }
    @RequestMapping(value = {"/message/submit/{receiverNick}"}, method = RequestMethod.GET)
    public ModelAndView messageReply(@PathVariable String receiverNick, Authentication auth){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("messageSendDetail");
        if(!receiverNick.equals("") || receiverNick != null){
            mav.addObject("receiverNick",receiverNick);
        }

        CustomUser customUser = (CustomUser) auth.getPrincipal();
        String nick = customUser.getLoginVO().getNick();

        mav.addObject("userNick",nick);
        return mav;
    }

    @ResponseBody
    @RequestMapping("/message/read/{messageNumber}")
    public boolean messageRead(@PathVariable int messageNumber){
        return messengerListService.messageRead(messageNumber);
    }
    @ResponseBody
    @RequestMapping(value = {"/message/receive/delete"}, method = RequestMethod.POST)
    public boolean messageReceiveDelete(@RequestBody List<String> deleteList){

        int size = deleteList.size();
        List<Integer> deleteNum = new ArrayList<Integer>();

        for(int i = 0; i < size; i++){
            deleteNum.add(Integer.parseInt(deleteList.get(i)));
        }
        Map<String,Object> deleteInfo = new HashMap<String, Object>();
        deleteInfo.put("messageType","receive");
        deleteInfo.put("list",deleteNum);

        boolean result = messengerListService.messageDelete(deleteInfo);
        return result;
    }
    @ResponseBody
    @RequestMapping(value = {"/message/send/delete"}, method = RequestMethod.POST)
    public boolean messageSendDelete(@RequestBody List<String> deleteList){

        int size = deleteList.size();
        List<Integer> deleteNum = new ArrayList<Integer>();

        for(int i = 0; i < size; i++){
            deleteNum.add(Integer.parseInt(deleteList.get(i)));
        }
        Map<String,Object> deleteInfo = new HashMap<String, Object>();
        deleteInfo.put("messageType","send");
        deleteInfo.put("list",deleteNum);

        boolean result = messengerListService.messageDelete(deleteInfo);
        return result;
    }


}
