package com.moa.model.service;

import com.moa.model.vo.ReportAdminVO;
import com.moa.model.vo.SimpleReportAdminVO;

import java.util.List;

public interface AdminReportSearchService {
    ReportAdminVO reportInfo(int reportId);
    List<SimpleReportAdminVO> reportList();
}
