package com.moa.controller;

import com.moa.model.service.CategoryService;
import com.moa.model.service.PriceService;
import com.moa.model.service.StoreRequestService;
import com.moa.model.vo.CustomUser;
import com.moa.model.vo.StoreRequestVO;
import lombok.extern.log4j.Log4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@Log4j
@RequestMapping("/entrust")
public class StoreRequestController {
    private Log logger = LogFactory.getLog(StoreRequestController.class);

    @Autowired
    private StoreRequestService storeRequestService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private PriceService priceService;

    @RequestMapping(value = "/{articleNum}", method = RequestMethod.GET)
    public ModelAndView entrustForm(@PathVariable("articleNum") int articleNum) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("entrust");

        List<Double> multiplePriceList = priceService.getMultiplePriceList();
        mav.addObject("multiplePriceList", multiplePriceList);

        List<Integer> detailPriceList = priceService.getDetailPriceList(articleNum);
        mav.addObject("detailPriceList", detailPriceList);

        List<String> categoryList = categoryService.getCategoryList();
        mav.addObject("categoryList", categoryList);
        return mav;
    }

    @RequestMapping(value = "/{articleNum}", method = RequestMethod.POST)
    public @ResponseBody
    boolean entrust(@PathVariable("articleNum") int articleNum,
                    StoreRequestVO storeRequestVO, Authentication auth) {
        CustomUser customUser = (CustomUser) auth.getPrincipal();
        int userId = Integer.parseInt(customUser.getLoginVO().getUserId());

        log.info("start");
        log.info(articleNum);
        log.info(storeRequestVO);

        storeRequestVO.setUserId(userId);
        storeRequestVO.setArticleNum(articleNum);

        logger.info("=====================================");
        logger.info("StoreRequest : " + storeRequestVO);
        if (storeRequestVO.getAttachList() != null)
            storeRequestVO.getAttachList().forEach(storeRequestAttachFileVO -> logger.info(storeRequestAttachFileVO.toString()));
        logger.info("=====================================");
        return storeRequestService.insert(storeRequestVO);

    }
}
