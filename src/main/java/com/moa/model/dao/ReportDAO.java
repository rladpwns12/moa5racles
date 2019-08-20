package com.moa.model.dao;

import com.moa.model.vo.ReportVO;

import java.util.Map;

public interface ReportDAO {
    boolean insertReport(ReportVO reportVO);
    Map<String, Object> selectUserReportList(Map<String, Object> map);
    ReportVO selectUserReport(long reportId);
}
