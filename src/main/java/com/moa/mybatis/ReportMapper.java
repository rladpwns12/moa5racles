package com.moa.mybatis;

import com.moa.model.vo.ReportAdminVO;
import com.moa.model.vo.ReportResultVO;
import com.moa.model.vo.ReportVO;
import com.moa.model.vo.SimpleReportAdminVO;

import java.util.List;
import java.util.Map;

public interface ReportMapper {
    int createUserReport(ReportVO reportVO);
    int searchUserReportTotPageNum(int userId);
    List<ReportVO> searchUserReportList(Map<String, Object> map);
    ReportVO searchUserReport(long reportId);
    ReportResultVO searchResult(long reportId);
    ReportAdminVO selectHostConfirm(int reportId);
    List<SimpleReportAdminVO> selectHostConfirmList();
}
