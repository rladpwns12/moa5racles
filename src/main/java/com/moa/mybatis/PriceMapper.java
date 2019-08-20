package com.moa.mybatis;

import java.util.List;

public interface PriceMapper {
    List<Double> selectMultiplePrice();
    List<Integer> selectDetailPrice(int articleNum);
}
