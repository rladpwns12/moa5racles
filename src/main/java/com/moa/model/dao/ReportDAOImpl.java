package com.moa.model.dao;

import com.moa.model.vo.ReportVO;
import com.moa.mybatis.ReportMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
