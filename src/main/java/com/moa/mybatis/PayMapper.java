package com.moa.mybatis;

import com.moa.model.vo.PayVO;
import com.moa.model.vo.SimpleAddressVO;

public interface PayMapper {
    boolean updateHistory(PayVO payVO);
}
