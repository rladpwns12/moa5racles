package com.moa.controller;


import com.moa.model.service.AddressSearchService;
import com.moa.model.service.HostRegistrationService;
import com.moa.model.vo.CustomUser;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import java.util.*;

@Controller
@Log4j
public class RegistHostController {
    @Autowired
    private HostRegistrationService hostRegistrationService;
    @Autowired
    private AddressSearchService addressSearchService;

    @RequestMapping(value="/registhost", method = RequestMethod.GET)
    public ModelAndView registHost(Authentication auth){
        ModelAndView mav = new ModelAndView();
        Map<String, Object> addressInfo = null;
        int userId;
        CustomUser customUser = (CustomUser) auth.getPrincipal();

        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
        while(iter.hasNext()){
            GrantedAuthority authority = iter.next();
        }

        if(customUser.getLoginVO().getFlag() == null){
            userId = Integer.parseInt(customUser.getLoginVO().getUserId());
            addressInfo =  addressSearchService.searchAddress(userId);
            if(addressInfo != null) {
                mav.setViewName("registHost");
                mav.addObject("addressId", addressInfo.get("addressId"));
                mav.addObject("address", addressInfo.get("address"));
            }else{
                //사용자 주소를 가져오지 못할 경우
            }
        }else{
            mav.setViewName("/main");
        }
        return mav;
    }

    @RequestMapping(value="/registhost", method = RequestMethod.POST)
    @ResponseBody
    public String registHostProc(@RequestParam (value="storage_type") String storagetype,
                                 @RequestParam (value="other_text") String otherText,
                                 @RequestParam (value="origin_or_new") String originOrNew,
                                 @RequestParam (value="address_id") int addressId,
                                 @RequestParam (value="postcode") String postCode,
                                 @RequestParam (value="address") String baseAddress,
                                 @RequestParam (value="detailAddress") String detailAddress,
                                 @RequestParam (value="company_name") String businessName,
                                 @RequestParam (value="company_registration_name") String registrationNum,
                                 @RequestParam (value="company_representative_name") String representative,
                                 @RequestParam (value="latitude") double latitude,
                                 @RequestParam (value="longitude") double longitude,
                                 Authentication auth){
        int userId;

        CustomUser customUser = (CustomUser) auth.getPrincipal();
        userId = Integer.parseInt(customUser.getLoginVO().getUserId());

        if(customUser.getLoginVO().getFlag() == null){
            Map<String, Object> hostInfo = new HashMap<String, Object>();

            hostInfo.put("userId", userId);
            hostInfo.put("addressId", addressId);
            hostInfo.put("storageType", storagetype);
            hostInfo.put("originOrNew", originOrNew);
            hostInfo.put("businessName", businessName);
            hostInfo.put("registrationNum", registrationNum);
            hostInfo.put("representative", representative);
            hostInfo.put("otherText", otherText);
            hostInfo.put("postCode", postCode);
            hostInfo.put("baseAddress", baseAddress);
            hostInfo.put("detailAddress", detailAddress);
            hostInfo.put("longitude", longitude);
            hostInfo.put("latitude", latitude);

            if(hostRegistrationService.registHost(hostInfo)){
                List<GrantedAuthority> updatedAuthorities = new ArrayList<>(auth.getAuthorities());
                updatedAuthorities.add(new SimpleGrantedAuthority("ROLE_PRE_HOST"));
                Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), updatedAuthorities);
                SecurityContextHolder.getContext().setAuthentication(newAuth);

                return "success";
            }else{
                return "fail";
            }
        }else {
            return "/main";
        }
    }
}
