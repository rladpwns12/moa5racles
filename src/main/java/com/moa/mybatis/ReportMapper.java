package com.moa.mybatis;

import com.moa.model.vo.ReportAdminVO;
import com.moa.model.vo.ReportVO;
import com.moa.model.vo.SimpleReportAdminVO;

import java.util.List;

public interface ReportMapper {
    int createUserReport(ReportVO reportVO);
    ReportAdminVO selectHostConfirm(int reportId);
    List<SimpleReportAdminVO> selectHostConfirmList();
}
