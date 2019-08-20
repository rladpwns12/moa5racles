package com.moa.model.service;

import com.moa.model.dao.PriceDAO;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j
public class PriceServiceImpl implements PriceService {
    @Autowired
    private PriceDAO priceDAO;

    @Override
    public List<Double> getMultiplePriceList() {
        List<Double> multiplePriceList = priceDAO.getMultiplePriceList();
        return multiplePriceList;
    }

    @Override
    public List<Integer> getDetailPriceList(int articleNum) {
        List<Integer> detailPriceList = priceDAO.getDetailPriceList(articleNum);
        return detailPriceList;
    }
}
