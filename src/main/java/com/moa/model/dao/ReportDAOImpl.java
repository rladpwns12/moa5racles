package com.moa.model.dao;

import com.moa.model.vo.ReportAdminVO;
import com.moa.model.vo.ReportResultVO;
import com.moa.model.vo.ReportVO;
import com.moa.model.vo.SimpleReportAdminVO;
import com.moa.mybatis.ReportMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ReportDAOImpl implements ReportDAO {
    @Autowired
    private SqlSession sqlSession;

    public boolean insertReport(ReportVO reportVO){
        ReportMapper mapper = sqlSession.getMapper(ReportMapper.class);

        try{
            mapper.createUserReport(reportVO);
        }catch(Exception e){
            return false;
        }
        return true;
    }

    @Override
    public Map<String, Object> selectUserReportList(Map<String, Object> map) {
        Map<String, Object> result = new HashMap<>();
        ReportMapper mapper = sqlSession.getMapper(ReportMapper.class);

        List<ReportVO> reportList = mapper.searchUserReportList(map);
        int totPageCnt = mapper.searchUserReportTotPageNum((Integer) map.get("userId"));

        result.put("reportList", reportList);
        result.put("totPageCnt", totPageCnt);

        return result;
    }

    @Override
    public Map<String, Object> selectUserReport(long reportId) {
        ReportMapper mapper = sqlSession.getMapper(ReportMapper.class);
        Map<String, Object> info = new HashMap<>();

        ReportVO reportVO = mapper.searchUserReport(reportId);
        info.put("reportVO", reportVO);
        if(reportVO.isReportState()){
            ReportResultVO reportResultVO = mapper.searchResult(reportId);
            info.put("reportResultVO", reportResultVO);
        }

        return info;
    }

    @Override
    public ReportAdminVO selectHostConfirm(int reportId) {
        ReportMapper mapper = sqlSession.getMapper(ReportMapper.class);
        return mapper.selectHostConfirm(reportId);
    }

    @Override
    public List<SimpleReportAdminVO> selectHostConfirmList() {
        ReportMapper mapper = sqlSession.getMapper(ReportMapper.class);
        return mapper.selectHostConfirmList();
    }
}
