package com.moa.model.init;

import com.moa.model.vo.AttachFileVO;
import com.moa.model.vo.StoreBoardAttachFileVO;
import com.moa.model.vo.StoreRequestAttachFileVO;
import com.moa.model.vo.UserAttachFileVO;

public class AttachVOFactory {
    private static final String STOREBOARD="storeBoard";
    private static final String STOREREQUEST="storeRequest";
    private static final String USER="user";

    public static AttachFileVO init(String key){
        switch (key){
            case STOREBOARD:
                return new StoreBoardAttachFileVO();
            case STOREREQUEST:
                return new StoreRequestAttachFileVO();
            case USER:
                return new UserAttachFileVO();
            default:
                return null;
        }
    }
}
