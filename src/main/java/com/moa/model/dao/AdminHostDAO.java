package com.moa.model.dao;

import com.moa.model.vo.AdminHostSimpleVO;

import java.util.List;

public interface AdminHostDAO {
    List<AdminHostSimpleVO> searchAdminHost();

//    AdminHostDetailVO searchAdminHostDetail(int articleNum);
//
//    AdminHostStoreVO searchAdminHostStore(int articleNum);
//
//    AdminHostEtcVO searchAdminHostEtc(int articleNum);
}
