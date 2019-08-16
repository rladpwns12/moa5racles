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

    public List<AttachFileVO> findByArticleNumSB(Long articleNum);
    public List<AttachFileVO> findByArticleNumSR(Long articleNum);
    public AttachFileVO findByUserId(Long userId);

    public List<AttachFileVO> getOldFilesSB();
    public List<AttachFileVO> getOldFilesSR();
    public List<AttachFileVO> getOldFilesUser();
}
