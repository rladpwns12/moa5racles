package com.moa.model.service;

import com.moa.model.dao.PayDAO;
import com.moa.model.vo.PayVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayServiceImpl implements PayService {
    @Autowired
    private PayDAO payDAO;

    @Override
    public boolean updateHistory(PayVO payvo) {
        return payDAO.updateHistory(payvo);
    }
}
