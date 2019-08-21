package com.moa.model.service;

import com.moa.model.dao.ReportDAO;
import com.moa.model.vo.ReportVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class FindReportServiceImpl implements FindReportService {
    @Autowired
    private ReportDAO reportDAO;

    @Override
    public Map<String, Object> findReportList(Map<String, Object> map) {
        return reportDAO.selectUserReportList(map);
    }

    @Override
    public Map<String, Object> findReport(long reportId) {
        return reportDAO.selectUserReport(reportId);
    }


}
