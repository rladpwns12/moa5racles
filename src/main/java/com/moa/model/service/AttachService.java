package com.moa.model.service;

import com.moa.model.vo.AttachFileVO;

import java.util.List;

public interface AttachService {
    public boolean insertAttach(List<AttachFileVO> attachFileList);
    public AttachFileVO getUserProfile(Long id);
}
