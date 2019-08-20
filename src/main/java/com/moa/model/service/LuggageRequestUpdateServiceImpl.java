package com.moa.model.service;

import com.moa.model.dao.StoreRequestDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LuggageRequestUpdateServiceImpl implements LuggageRequestUpdateService {
    @Autowired
    private StoreRequestDAO storeRequestDAO;

    @Override
    public boolean deleteRequest(int articleNum) {
        if(storeRequestDAO.deleteConfrimdoneRequest(articleNum) >= 1){
            return true;
        }
        return false;
    }
}
