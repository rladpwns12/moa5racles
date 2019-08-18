package com.moa.model.service;

import com.moa.model.dao.CategoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDAO categoryDAO;
    @Override
    public List<String> getCategoryList() {
        return categoryDAO.getCategoryList();
    }
}
