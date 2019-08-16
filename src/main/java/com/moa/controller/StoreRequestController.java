package com.moa.controller;

import com.moa.file.FileUpload;
import com.moa.model.service.CategoryService;
import com.moa.model.service.StoreRequestService;
import com.moa.model.vo.CustomUser;
import com.moa.model.vo.StoreRequestVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/entrust")
public class StoreRequestController {
    private Log logger= LogFactory.getLog(StoreRequestController.class);

    @Autowired
    private StoreRequestService storeRequestService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/{articleNum}" , method = RequestMethod.GET)
    public ModelAndView entrustForm(@PathVariable("articleNum") int articleNum){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("entrust");
        List<String> categoryList = categoryService.getCategoryList();
        mav.addObject("categoryList",categoryService.getCategoryList());
        return mav;
    }

    @RequestMapping(value = "/{articleNum}" , method = RequestMethod.POST)
    public @ResponseBody boolean entrust(@PathVariable("articleNum") int articleNum,
                                          StoreRequestVO storeRequestVO, Authentication auth) {
        CustomUser customUser = (CustomUser) auth.getPrincipal();
        int userId=Integer.parseInt(customUser.getLoginVO().getUserId());

        storeRequestVO.setUserId(userId);
        storeRequestVO.setArticleNum(articleNum);

        logger.info("=====================================");
        logger.info("StoreRequest : "+storeRequestVO);
        if(storeRequestVO.getAttachList()!=null)
            storeRequestVO.getAttachList().forEach(storeRequestAttachFileVO -> logger.info(storeRequestAttachFileVO.toString()));
        logger.info("=====================================");
        return storeRequestService.insert(storeRequestVO);

    }
}
