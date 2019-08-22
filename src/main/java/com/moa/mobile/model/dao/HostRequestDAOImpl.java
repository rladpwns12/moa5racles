package com.moa.mobile.model.dao;

import com.moa.mobile.model.vo.RequestListInfo;

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
    public List<RequestListInfo> requestList(int hostId) {
        HostRequestMapper mapper = sqlSession.getMapper(HostRequestMapper.class);
        System.out.println(mapper);
        return mapper.HostRequestList(hostId);
    }
}
