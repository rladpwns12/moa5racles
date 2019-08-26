package com.moa.model.dao;

import com.moa.model.vo.PayVO;
import com.moa.mybatis.PayMapper;
import lombok.extern.log4j.Log4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Log4j
public class PayDAOImpl implements PayDAO {
    @Autowired
    @Qualifier("sqlSession_oracle")
    private SqlSession sqlSession_oracle;

    @Override
    public boolean updateHistory(PayVO payVO) {
        PayMapper mapper = sqlSession_oracle.getMapper(PayMapper.class);
        boolean result;
        result = mapper.updateHistory(payVO);
        return result;
    }
}
