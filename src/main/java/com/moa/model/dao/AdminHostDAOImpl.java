package com.moa.model.dao;

import com.moa.model.vo.*;
import com.moa.mybatis.AdminHostMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AdminHostDAOImpl implements AdminHostDAO {
    @Autowired
    @Qualifier("sqlSession_oracle")
    private SqlSession sqlSession_oracle;

    @Override
    public List<AdminHostSimpleVO> searchAdminHost() {
        AdminHostMapper mapper = sqlSession_oracle.getMapper(AdminHostMapper.class);
        List<AdminHostSimpleVO> adminHostAry = mapper.selectHostConfirmList();
        return adminHostAry;
    }
    @Override
    public Map<String,Object> searchAdminHostDetail(int userId, String storageType) {
        AdminHostMapper mapper=sqlSession_oracle.getMapper(AdminHostMapper.class);
        HashMap<String,Object> info = new HashMap<>();
        info.put("userId",userId);
        info.put("storageType",storageType);
        return mapper.selectConfirmDetail(info);
    }
    @Override
    public boolean processConfirm(Map<String, Object> info) {
        AdminHostMapper mapper = sqlSession_oracle.getMapper(AdminHostMapper.class);
        return mapper.processConfirm(info) != 0 ? true : false;
    }

    @Override
    public boolean processRefuse(Map<String, Object> info) {
        AdminHostMapper mapper = sqlSession_oracle.getMapper(AdminHostMapper.class);
        return mapper.processRefuse(info) != 0 ? true : false;
    }


}
