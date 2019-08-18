package com.moa.model.service;

import com.moa.model.dao.AttachDAO;
import com.moa.model.vo.AttachFileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AttachServiceImpl implements AttachService {
    @Autowired
    private AttachDAO attachDAO;
    @Override
    @Transactional
    public boolean insertAttach(List<AttachFileVO> attachFileList) {
        attachFileList.forEach(vo ->attachDAO.insertAttach(vo));
        return true;
    }

    @Override
    public AttachFileVO getUserProfile(Long id) {
        return attachDAO.searchByUserId(id);
    }
}
