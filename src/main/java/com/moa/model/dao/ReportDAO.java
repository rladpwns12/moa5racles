package com.moa.model.dao;

import com.moa.model.vo.ReportAdminVO;
import com.moa.model.vo.ReportVO;
import com.moa.model.vo.SimpleReportAdminVO;

import java.util.List;

public interface ReportDAO {
    boolean insertReport(ReportVO reportVO);
    ReportAdminVO selectHostConfirm(int reportId);
    List<SimpleReportAdminVO> selectHostConfirmList();
}
