package com.moa.controller;

import com.moa.file.FileUpload;
import com.moa.message.PathMessage;
import com.moa.model.service.LuggageWelcomeService;
import com.moa.model.service.StoreBoardSearchService;
import com.moa.model.service.StoreBoardService;
import com.moa.model.vo.CustomUser;
import com.moa.model.vo.DetailOptionVO;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@RequestMapping("/storeboard")
@Controller
@Log4j
public class StoreBoardController {
    @Autowired
    private StoreBoardService storeBoardService;
    @Autowired
    private LuggageWelcomeService luggageWelcomeService;
    @Autowired
    private StoreBoardSearchService storeBoard;

    @RequestMapping(value="" , method=  RequestMethod.GET)
    public String hostSearch(){
        return "hostSearch";
    }

    @RequestMapping(value="/Search" , method=  RequestMethod.POST)
    public @ResponseBody
    List<Object> hostSearch2(DetailOptionVO detail){


        List<Object> documents = storeBoard.search(detail);
        //System.out.println(documents.get(0));

        return documents;
    }

    @RequestMapping(value = "/keep", method = RequestMethod.GET)
    public ModelAndView registerStoreBoard(Authentication auth) {
        ModelAndView mav = new ModelAndView();
        String hostId;
        mav.setViewName("keep");

        CustomUser customUser = (CustomUser) auth.getPrincipal();
        hostId = customUser.getLoginVO().getUserId();

        mav.addObject("hostId", hostId);
        Map<String, Object> map = luggageWelcomeService.initBoard(hostId);
        mav.addObject("map", map);
        return mav;
    }

    @RequestMapping(value = "/keep", method = RequestMethod.POST)
    public @ResponseBody void keepRegisterStoreBoard(HttpServletRequest request, Authentication auth) {
        String hostId;

        CustomUser customUser = (CustomUser) auth.getPrincipal();
        hostId = customUser.getLoginVO().getUserId();
        Map<String, Object> articleMap = FileUpload.keepUpload(request);
        articleMap.put("hostId", hostId);
        System.out.println("Result: " + luggageWelcomeService.noticeStorage(articleMap));
    }

    @RequestMapping("/{articleNum}")
    public ModelAndView retrieveStoreBoard(@PathVariable("articleNum") int articleNum, String distance){
        System.out.println(distance);
        ModelAndView mav=new ModelAndView();
        mav.setViewName(PathMessage.STORE_BOARD_ONE);
        mav.addObject("distance",distance);
        mav.addObject("storeBoard",storeBoardService.selectStorage(articleNum));
        return mav;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception e){
        ModelAndView mav=new ModelAndView();
        e.printStackTrace();
        mav.setViewName("/error/page");
        mav.addObject("message","존재하지 않는 페이지 입니다.");
        return mav;
    }

    @RequestMapping(value="/{articleNum}/delete",method = RequestMethod.POST)
    public @ResponseBody boolean removeStoreBoard(@PathVariable("articleNum") int articleNum, @RequestBody String nick, Authentication auth){
        CustomUser customUser = (CustomUser) auth.getPrincipal();
        if(!nick.equals(customUser.getLoginVO().getNick()))
            return false;
        boolean flag = storeBoardService.deleteStorage(articleNum);
        return flag;
    }
}
