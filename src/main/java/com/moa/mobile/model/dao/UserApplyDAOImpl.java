package com.moa.mobile.model.dao;

import com.moa.mobile.model.vo.ApplyListInfoVO;
import com.moa.mybatis.mobile.HostRequestMapper;
import com.moa.mybatis.mobile.UserApplyMapper;
import lombok.NoArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@NoArgsConstructor
public class UserApplyDAOImpl implements UserApplyDAO {
    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<ApplyListInfoVO> userApplyList(int userId) {
        UserApplyMapper mapper = sqlSession.getMapper(UserApplyMapper.class);
        return mapper.userApplyList(userId);
    }
}
