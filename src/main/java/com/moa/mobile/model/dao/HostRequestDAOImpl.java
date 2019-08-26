package com.moa.mobile.model.dao;

import com.moa.mobile.model.vo.RequestListInfoVO;

import com.moa.mybatis.mobile.HostRequestMapper;
import lombok.NoArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@NoArgsConstructor
public class HostRequestDAOImpl implements HostRequestDAO{
    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<RequestListInfoVO> requestList(int hostId) {
        HostRequestMapper mapper = sqlSession.getMapper(HostRequestMapper.class);
        return mapper.hostRequestList(hostId);
    }
}
