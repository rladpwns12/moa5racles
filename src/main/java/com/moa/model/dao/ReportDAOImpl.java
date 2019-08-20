package com.moa.model.dao;

import com.moa.model.vo.ReportAdminVO;
import com.moa.model.vo.ReportVO;
import com.moa.model.vo.SimpleReportAdminVO;
import com.moa.mybatis.ReportMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
