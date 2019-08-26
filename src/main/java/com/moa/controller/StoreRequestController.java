package com.moa.controller;

import com.moa.model.service.CategoryService;
import com.moa.model.service.PriceService;
import com.moa.model.service.StoreRequestService;
import com.moa.model.vo.CustomUser;
import com.moa.model.vo.StoreRequestVO;
import com.moa.valid.CollectionValidator;
import lombok.extern.log4j.Log4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.ValidationException;
import java.util.List;

@Controller
@RequestMapping("/entrust")
@Log4j
public class StoreRequestController {
    private Log logger = LogFactory.getLog(StoreRequestController.class);

    @Autowired
    private StoreRequestService storeRequestService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private PriceService priceService;
    @Autowired
    private CollectionValidator collectionValidator;

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
                    StoreRequestVO storeRequestVO, Authentication auth, BindingResult bindingResult) {
        collectionValidator.validate(storeRequestVO, bindingResult);

        if (bindingResult.hasErrors()) {
            return false;
        }
        CustomUser customUser = (CustomUser) auth.getPrincipal();
        int userId = Integer.parseInt(customUser.getLoginVO().getUserId());

        if(storeRequestService.isOwnsBoard(userId,articleNum))
            return false;

        storeRequestVO.setUserId(userId);
        storeRequestVO.setArticleNum(articleNum);

        return storeRequestService.insert(storeRequestVO);

    }
}
