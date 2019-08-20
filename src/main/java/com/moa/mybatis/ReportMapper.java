package com.moa.mybatis;

import com.moa.model.vo.ReportAdminVO;
import com.moa.model.vo.ReportVO;
import com.moa.model.vo.SimpleReportAdminVO;

import java.util.List;
import java.util.Map;

public interface ReportMapper {
    int createUserReport(ReportVO reportVO);
    ReportAdminVO selectHostConfirm(int reportId);
    List<SimpleReportAdminVO> selectHostConfirmList();
    int insertResultReport(Map<String,Object> insertInfo);
}
