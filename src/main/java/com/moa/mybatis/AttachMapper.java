package com.moa.mybatis;

import com.moa.model.vo.AttachFileVO;
import com.moa.model.vo.StoreBoardAttachFileVO;
import com.moa.model.vo.StoreRequestAttachFileVO;
import com.moa.model.vo.UserAttachFileVO;

import java.util.List;

public interface AttachMapper {
    public int insertStoreBoard(AttachFileVO vo);
    public int insertStoreRequest(AttachFileVO vo);
    public int insertUser(AttachFileVO vo);

    public int deleteStoreBoard(String uuid);
    public int deleteStoreRequest(String uuid);
    public int deleteUser(String uuid);

    public List<StoreBoardAttachFileVO> findByArticleNumSB(Long articleNum);
    public List<StoreRequestAttachFileVO> findByArticleNumSR(Long articleNum);
    public UserAttachFileVO findByUserId(Long userId);
}
