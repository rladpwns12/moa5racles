package com.moa.controller;

import com.moa.message.TransactionStateMessaage;
import com.moa.model.service.LuggageReceiveRecordServiceImpl;
import com.moa.model.service.LuggageRequestApproveServiceImpl;
import com.moa.model.service.LuggageRequestInfoService;
import com.moa.model.service.MyStorageServiceImpl;
import com.moa.model.vo.CustomUser;
import com.moa.model.vo.ReadStoreRequestVO;
import com.moa.model.vo.SimpleHostRequestVO;
import com.moa.model.vo.SimpleStorageBoardVO;
import com.moa.paging.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/hostpage")
public class HostPageController {
    @Autowired
    private LuggageReceiveRecordServiceImpl receiveService;
    @Autowired
    private LuggageRequestApproveServiceImpl requestService;
    @Autowired
    private LuggageRequestInfoService luggageRequestInfoService;
    @Autowired
    private MyStorageServiceImpl myStorageService;

    @RequestMapping("")
    public String hostPage(){
        return "confirmyet";
    }

    //승인대기====================================================================
    @RequestMapping(value = "/confirmyet",method = RequestMethod.GET)
    public String confirmYet(){
        return "confirmyet";
    }


    @ResponseBody
    @RequestMapping(value = "/confirmyet/list",method = RequestMethod.GET)
    public Map<String, Object> confirmYetUpdate(Authentication auth,
                                                @RequestParam("curPage") int curPage){

        CustomUser customUser = (CustomUser) auth.getPrincipal();
        int userId = Integer.parseInt(customUser.getLoginVO().getUserId());
        //*****************list logic************************
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("firstNum",(curPage-1)*10+1);
        map.put("lastNum",(curPage-1)*10+10);
        map.put("hostId",userId);
        map.put("stateType", TransactionStateMessaage.WAITING_APPROVE);
        List<SimpleHostRequestVO> list
                = receiveService.selectLuggageWaitingReceiveRecord(map);


        //******************paging logic*********************
        Map<String,Object> pagingInfo = new HashMap<String,Object>();
        pagingInfo.put("hostId",userId);
        pagingInfo.put("stateType", TransactionStateMessaage.WAITING_APPROVE);

        int listCnt = receiveService.selectLuggageWaitingReceiveRecordCnt(pagingInfo);
        Pagination pagination = new Pagination(listCnt,curPage,list.size());


        //결과
        Map<String,Object> result = new HashMap<String, Object>();
        result.put("list",list);
        result.put("pagination",pagination);

        return result;
    }

    @RequestMapping(value="/requestlist/info/{requestNum}", method = RequestMethod.GET)
    @ResponseBody
    public ReadStoreRequestVO myPageRequestInfo(@PathVariable("requestNum") int requestId){
        System.out.println(requestId);
        ReadStoreRequestVO requestVO = luggageRequestInfoService.selectLuggageRequestInfo(requestId);
        System.out.println(requestVO);
        requestVO.setApplicationDate(requestVO.getApplicationDate());
        return requestVO;
    }

    @RequestMapping(value = "/confirmyet/confirm", method= RequestMethod.GET)
    @ResponseBody
    public boolean confirmCheck(@RequestParam("articleNum") int articleNum,
                             @RequestParam("state") String state){
        boolean result;
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("requestNum",articleNum);
        if(state.equals("approve")){
            map.put("newState", TransactionStateMessaage.BEFORE_PAYMENT);
        }
        else{
            map.put("newState", TransactionStateMessaage.REJECT_APPROVE);
        }
        result = requestService.updateTransactionHistory(map);

        return result;
    }

    //승인완료====================================================================
    @RequestMapping(value = "/confirmdone",method = RequestMethod.GET)
    public String confirmDone(){

        return "confirmdone";
    }
    //승인완료/업데이트
    @ResponseBody
    @RequestMapping(value = "/confirmdone/list",method = RequestMethod.GET)
    public Map<String,Object> confirmDoneUpdate(Authentication auth,
                                                @RequestParam("curPage") int curPage){
        CustomUser customUser = (CustomUser) auth.getPrincipal();
        int userId = Integer.parseInt(customUser.getLoginVO().getUserId());


        //*****************list logic************************
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("firstNum",(curPage-1)*10+1);
        map.put("lastNum",(curPage-1)*10+10);
        map.put("hostId",userId);
        List<SimpleHostRequestVO> list
                = receiveService.selectLuggageWaitingReceiveRecord(map);

        //******************paging logic*********************
        Map<String,Object> pagingInfo = new HashMap<String,Object>();
        pagingInfo.put("hostId",userId);

        int listCnt = receiveService.selectLuggageWaitingReceiveRecordCnt(pagingInfo);
        Pagination pagination = new Pagination(listCnt,curPage,list.size());

        //결과
        Map<String,Object> result = new HashMap<String, Object>();
        result.put("list",list);
        result.put("pagination",pagination);

        return result;
    }

    //나의보관소====================================================================
    @RequestMapping(value = "/mystorage",method = RequestMethod.GET)
    public String myStorage(){
        return "myStorage";
    }

    @ResponseBody
    @RequestMapping(value = "/mystorage/list", method = RequestMethod.GET)
    public Map<String,Object> myStrotageList(Authentication auth,
                                  @RequestParam("curPage") int curPage){
        Map<String,Object> result = new HashMap<String, Object>();
        CustomUser customUser = (CustomUser) auth.getPrincipal();
        int userId = Integer.parseInt(customUser.getLoginVO().getUserId());
        //*****************list logic************************
        Map<String,Object> storageInfo = new HashMap<String,Object>();
        storageInfo.put("firstNum",(curPage-1)*10+1);
        storageInfo.put("lastNum",(curPage-1)*10+10);
        storageInfo.put("hostId",userId);

        List<SimpleStorageBoardVO> list = myStorageService.selectMyStorage(storageInfo);

        //*****************paging logic************************
        Map<String,Object> pagingInfo = new HashMap<String,Object>();
        pagingInfo.put("hostId",userId);
        int listCnt = myStorageService.selectMyStorageCnt(userId);


        Pagination pagination = new Pagination(listCnt,curPage,list.size());

        //결과
        result.put("list",list);
        result.put("pagination",pagination);
        System.out.println(result);
        return result;
    }

}
