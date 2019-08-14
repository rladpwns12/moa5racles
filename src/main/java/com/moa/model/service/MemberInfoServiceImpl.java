package com.moa.model.service;


import com.moa.model.dao.UserDAO;
import com.moa.model.vo.CustomUser;
import com.moa.model.vo.LoginVO;
import com.moa.model.vo.SimpleUserInfoVO;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("memberService")
@NoArgsConstructor
public class MemberInfoServiceImpl implements MemberInfoService, UserDetailsService {
    @Autowired
    private UserDAO userDAO;

    public boolean checkExistUser(String userNick) {
        return userDAO.checkExistUser(userNick);
    }

    public SimpleUserInfoVO selectMemberInfo(int userId) {
        return userDAO.selectUserInfo(userId);
    }

    public boolean signUpMember(Map<String, Object> userInfo) {
        return userDAO.signUpUser(userInfo);
    }

    public boolean signUpDuplicationCheck(Map<String, Object> duplicationInfo) {
        return userDAO.signUpDuplicationCheck(duplicationInfo);
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        LoginVO loginVO = userDAO.checkLogin(email);
        System.out.println("loadUserByUsername");
        if(loginVO==null) {
            throw new InternalAuthenticationServiceException(email);
        }
        return new CustomUser(loginVO);
    }

}
