package com.moa.model.service;

import java.util.List;

public interface PriceService {
    List<Double> getMultiplePriceList();
    List<Integer> getDetailPriceList(int articleNum);
}
