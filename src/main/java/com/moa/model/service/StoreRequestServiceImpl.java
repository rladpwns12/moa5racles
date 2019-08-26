package com.moa.model.service;

import com.moa.model.dao.AttachDAO;
import com.moa.model.dao.StoreRequestDAO;
import com.moa.model.vo.StoreRequestAttachFileVO;
import com.moa.model.vo.StoreRequestVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StoreRequestServiceImpl implements StoreRequestService {
    private Log log= LogFactory.getLog(StoreRequestService.class);
    @Autowired
    private StoreRequestDAO storeRequestDAO;
    @Autowired
    private AttachDAO attachDAO;

    @Override
    @Transactional
    public boolean insert(StoreRequestVO storeRequestVO) {
        if(!(storeRequestDAO.insert(storeRequestVO)>0))
            return false;

        Long id = new Long(storeRequestVO.getStoreRequestNum());
        List<StoreRequestAttachFileVO> attachList = storeRequestVO.getAttachList();
        if(attachList ==null || attachList.size()<=0)
            return false;

        attachList.forEach(attach -> {
            attach.setId(id);
            attachDAO.insertAttach(attach);
        });
        return true;
    }

    @Override
    public boolean isOwnsBoard(int userId, int articleNum) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("userId",userId);
        map.put("articleNum",articleNum);
        return storeRequestDAO.checkBoard(map);
    }
}
