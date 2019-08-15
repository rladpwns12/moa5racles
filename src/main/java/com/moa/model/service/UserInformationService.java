package com.moa.model.service;

import com.moa.model.vo.AddressVO;

public interface UserInformationService {
    AddressVO findUserAddress(int userId);
}
