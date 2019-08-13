package com.moa.model.service;


import com.moa.model.dao.UserDAO;
import com.moa.model.vo.LoginVO;
import com.moa.model.vo.SimpleUserInfoVO;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

public interface MemberInfoService {
    public boolean checkExistUser(String userNick);
    public SimpleUserInfoVO selectMemberInfo(int userId);
    public boolean signUpMember(Map<String, Object> userInfo);
    public boolean signUpDuplicationCheck(Map<String, Object> duplicationInfo);
}
