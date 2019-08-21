package com.moa.model.service;

import com.moa.model.vo.ReportVO;

import java.util.Map;

public interface FindReportService {
    Map<String, Object> findReportList(Map<String, Object>map);
    Map<String, Object> findReport(long reportId);
}
