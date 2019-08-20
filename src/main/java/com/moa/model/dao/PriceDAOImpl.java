package com.moa.model.dao;


import com.moa.mybatis.PriceMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PriceDAOImpl implements PriceDAO {
    @Autowired
    SqlSession sqlSession;

    @Override
    public List<Double> getMultiplePriceList() {
        PriceMapper mapper = sqlSession.getMapper(PriceMapper.class);
        return mapper.selectMultiplePrice();
    }

    @Override
    public List<Integer> getDetailPriceList(int articleNum) {
        PriceMapper mapper = sqlSession.getMapper(PriceMapper.class);
        return mapper.selectDetailPrice(articleNum);

    }
}
