package com.moa.model.dao;

import com.moa.mybatis.CategoryMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDAOImpl implements CategoryDAO {
    @Autowired
    SqlSession sqlSession;
    @Override
    public List<String> getCategoryList() {
        CategoryMapper mapper = sqlSession.getMapper(CategoryMapper.class);
        return mapper.selectCategory();
    }
}
