package com.moa.model.service;

import com.moa.model.dao.ReportDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AdminReportReplyServiceImpl implements AdminReportReplyService{
    @Autowired
    private ReportDAO reportDAO;
    @Override
    public boolean replyReport(Map<String, Object> replyInfo) {
        Integer result = reportDAO.insertResultReport(replyInfo);
        if(result >= 1){
            return true;
        }
        return false;
    }
}
