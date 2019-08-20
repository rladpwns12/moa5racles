package com.moa.model.service;

import com.moa.model.dao.ReportDAO;
import com.moa.model.vo.ReportVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MakeReportServiceImpl implements MakeReportService {
    @Autowired
    private ReportDAO reportDAO;

    @Override
    public boolean makeReport(ReportVO reportVO) {
        return reportDAO.insertReport(reportVO);
    }
}
