package com.moa.model.service;

import com.moa.model.dao.ReportDAO;
import com.moa.model.vo.ReportAdminVO;
import com.moa.model.vo.SimpleReportAdminVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminReportSearchServiceImpl implements AdminReportSearchService {
    @Autowired
    ReportDAO reportDAO;
    @Override
    public ReportAdminVO reportInfo(int reportId) {
        ReportAdminVO reportAdminVO = reportDAO.selectHostConfirm(reportId);
        return reportAdminVO;
    }

    @Override
    public List<SimpleReportAdminVO> reportList() {
        List<SimpleReportAdminVO> list = reportDAO.selectHostConfirmList();
        return list;

    }
}
