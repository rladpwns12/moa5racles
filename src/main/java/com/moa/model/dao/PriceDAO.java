package com.moa.model.dao;

import java.util.List;

public interface PriceDAO {
    List<Double> getMultiplePriceList();
    List<Integer> getDetailPriceList(int articleNum);
}
