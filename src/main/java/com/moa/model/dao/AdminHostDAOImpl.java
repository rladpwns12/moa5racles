package com.moa.model.dao;

import com.moa.model.vo.AdminHostSimpleVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminHostDAOImpl implements AdminHostDAO{
    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<AdminHostSimpleVO> searchAdminHost() {
        return null;
    }

//    @Override
//    public AdminHostDetailVO searchAdminHostDetail(int articleNum) {
//        return null;
//    }
//
//    @Override
//    public AdminHostStoreVO searchAdminHostStore(int articleNum) {
//        return null;
//    }
//
//    @Override
//    public AdminHostEtcVO searchAdminHostEtc(int articleNum) {
//        return null;
//    }
}
