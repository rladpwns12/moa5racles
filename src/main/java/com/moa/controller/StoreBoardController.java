package com.moa.controller;

import com.moa.message.PathMessage;
import com.moa.model.service.LuggageWelcomeService;
import com.moa.model.service.StoreBoardSearchService;
import com.moa.model.service.StoreBoardSearchServiceImpl;
import com.moa.model.service.StoreBoardService;
import com.moa.model.vo.CustomUser;
import com.moa.model.vo.DetailOptionVO;
import com.moa.model.vo.EntrustSearchVO;
import com.moa.model.vo.StoreBoardFormVO;
import com.moa.valid.CollectionValidator;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
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
    private StoreBoardSearchService storeBoardSearchService;
    @Autowired
    private CollectionValidator collectionValidator;

    @RequestMapping(value="" , method=  RequestMethod.GET)
    public String hostSearch(){
        return "hostSearch";
    }

    @RequestMapping(value="/Search" , method=  RequestMethod.GET)
    public @ResponseBody
    List<EntrustSearchVO> hostSearchByFilter(DetailOptionVO detail){
        List<EntrustSearchVO> documents = storeBoardSearchService.search(detail);
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
    public @ResponseBody boolean keepRegisterStoreBoard( StoreBoardFormVO storeBoardFormVO, Authentication auth,BindingResult bindingResult) {
        collectionValidator.validate(storeBoardFormVO,bindingResult);
        log.warn(storeBoardFormVO);
        if (bindingResult.hasErrors()) {
            log.warn(bindingResult.getAllErrors().toString());
            return false;
        }
        String hostId;
        CustomUser customUser = (CustomUser) auth.getPrincipal();
        hostId = customUser.getLoginVO().getUserId();
        storeBoardFormVO.setHostId(hostId);
       luggageWelcomeService.noticeStorage(storeBoardFormVO);
       return true;
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

    @RequestMapping(value="/{articleNum}/delete",method = RequestMethod.POST)
    public @ResponseBody boolean removeStoreBoard(@PathVariable("articleNum") int articleNum, @RequestBody String nick, Authentication auth){
        CustomUser customUser = (CustomUser) auth.getPrincipal();
        if(!nick.equals(customUser.getLoginVO().getNick()))
            return false;
        boolean flag = storeBoardService.deleteStorage(articleNum);
        return flag;
    }
}
