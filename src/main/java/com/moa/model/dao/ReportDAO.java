package com.moa.model.dao;

import com.moa.model.vo.ReportAdminVO;
import com.moa.model.vo.ReportVO;
import com.moa.model.vo.SimpleReportAdminVO;

import java.util.List;

import java.util.Map;

public interface ReportDAO {
    boolean insertReport(ReportVO reportVO);
    Map<String, Object> selectUserReportList(Map<String, Object> map);
    Map<String, Object> selectUserReport(long reportId);
    ReportAdminVO selectHostConfirm(int reportId);
    List<SimpleReportAdminVO> selectHostConfirmList();
}
